package com.example.cryptoExercise.service;


import com.example.cryptoExercise.dao.UserDao;
import com.example.cryptoExercise.model.userDto.User;
import com.example.cryptoExercise.model.userDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * JWTUserDetailsService implements the Spring Security UserDetailsService interface.
 * It overrides the loadUserByUsername for fetching user details from the database using the username.
 * The Spring Security Authentication Manager calls this method for getting the user details from the database when authenticating the user details provided by the user.
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    RestTemplate restTemplate = new RestTemplate();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  userDao.findByUsername(username);


        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());


        //hardcoded user without DB
 /*       if ("admin".equals(username)) {
            return new User("admin", "$2a$10$KlxfOiCRwsT3LDa5/hq6XeptqeVOM/phIBAQYB4FpQgvikN7Ypa3y",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }*/


    }

    public User save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(bcryptEncoder.encode(userDto.getPassword()));
        return userDao.save(user);
    }

}