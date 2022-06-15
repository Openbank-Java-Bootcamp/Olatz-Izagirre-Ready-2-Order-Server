package com.ironhack.finalprojectserver.security;

import com.ironhack.finalprojectserver.filter.CustomAuthenticationFilter;
import com.ironhack.finalprojectserver.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/auth/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/orderItems/visibles").permitAll();
        http.authorizeRequests().antMatchers("/auth/signup").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/users").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/eatingTables").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/eatingTables/{id}").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/eatingTables").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT,"/api/eatingTables/{id}").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/foodOrders").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/waiters").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"/api/waiters/eatingTables").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(GET,"/api/foodOrders/eatingTables/{id}").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(POST,"/api/foodOrders").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(GET,"/api/foodOrders/{id}").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(GET,"/api/foodOrders/cooked/waiter").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(GET,"/api/foodOrders/served/waiter").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(PATCH,"/api/foodOrders/{id}").hasAnyAuthority("WAITER","CHEF");
        http.authorizeRequests().antMatchers(PUT,"/api/foodOrders/{id}").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(DELETE,"/api/foodOrders/{id}").hasAnyAuthority("WAITER");
        http.authorizeRequests().antMatchers(GET,"/api/foodOrders/status/ordered").hasAnyAuthority("CHEF");
        http.authorizeRequests().antMatchers(POST,"/api/orderItems").hasAnyAuthority("CHEF");
        http.authorizeRequests().antMatchers(GET,"/api/orderItems").hasAnyAuthority("CHEF");
        http.authorizeRequests().antMatchers(PATCH,"/api/orderItems/{id}").hasAnyAuthority("CHEF");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
