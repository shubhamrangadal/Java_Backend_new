package com.fr.epita.javabackend.controller;

import com.fr.epita.javabackend.model.User;
import com.fr.epita.javabackend.repo.SeenMoviesRespoitory;
import com.fr.epita.javabackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //write getmapping function to get user details by username and password
    @GetMapping("/users/{userName}/{password}")
    public User checkUser(@PathVariable String userName,@PathVariable String password){
        return userRepository.getUserDetails(userName,password);
    }
}
