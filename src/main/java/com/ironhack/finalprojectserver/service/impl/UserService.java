package com.ironhack.finalprojectserver.service.impl;

import com.ironhack.finalprojectserver.DTO.UserSignupDTO;
import com.ironhack.finalprojectserver.model.Admin;
import com.ironhack.finalprojectserver.model.Chef;
import com.ironhack.finalprojectserver.model.User;
import com.ironhack.finalprojectserver.model.Waiter;
import com.ironhack.finalprojectserver.repository.*;
import com.ironhack.finalprojectserver.service.interfaces.UserServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserService implements UserServiceInterface, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ChefRepository chefRepository;
    @Autowired
    private WaiterRepository waiterRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User saveUser(UserSignupDTO userSignupDTO) {
        if (userRepository.findByEmail(userSignupDTO.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists");
        }
        log.info("Saving a new user {} inside of the database", userSignupDTO.getName());
        if (Objects.equals(userSignupDTO.getRole(), "ADMIN")) {
            Admin admin = new Admin();
            admin.setName(userSignupDTO.getName());
            admin.setEmail(userSignupDTO.getEmail());
            admin.setPassword(passwordEncoder.encode(userSignupDTO.getPassword()));
            admin.setRole(roleRepository.findByName(userSignupDTO.getRole()));
            return adminRepository.save(admin);
        } else if (Objects.equals(userSignupDTO.getRole(), "CHEF")) {
            Chef chef = new Chef();
            chef.setName(userSignupDTO.getName());
            chef.setEmail(userSignupDTO.getEmail());
            chef.setPassword(passwordEncoder.encode(userSignupDTO.getPassword()));
            chef.setRole(roleRepository.findByName(userSignupDTO.getRole()));
            return chefRepository.save(chef);
        } else {
            Waiter waiter = new Waiter();
            waiter.setName(userSignupDTO.getName());
            waiter.setEmail(userSignupDTO.getEmail());
            waiter.setPassword(passwordEncoder.encode(userSignupDTO.getPassword()));
            waiter.setRole(roleRepository.findByName(userSignupDTO.getRole()));
            return waiterRepository.save(waiter);
        }
    }

    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User is found in the database: {}", email);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
    }
}
