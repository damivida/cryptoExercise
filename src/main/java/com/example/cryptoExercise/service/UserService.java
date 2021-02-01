package com.example.cryptoExercise.service;


import com.example.cryptoExercise.dao.UserDao;
import com.example.cryptoExercise.model.userDto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
         userDao.save(user);
    }
}
