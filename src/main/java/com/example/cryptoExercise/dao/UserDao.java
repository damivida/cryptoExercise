package com.example.cryptoExercise.dao;


import com.example.cryptoExercise.model.userDto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDao is an interface that extends the Spring Framework class CrudRepository.
 * CrudRepository class is a generics and takes the following two parameters as arguments- What type of Object will this repository be working with-
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
