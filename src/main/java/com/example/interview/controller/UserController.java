package com.example.interview.controller;

import com.example.interview.entity.User;
import com.example.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add_user" )
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {

        return userService.saveUser(user);
    }

    @GetMapping("/user_details/{email}" )
    public User saveUser(@PathVariable String email) throws ExecutionException, InterruptedException {

        return userService.getUserDetails(email);
    }

    @PutMapping("/update_user" )
    public String updateUser(@RequestBody User user) throws ExecutionException, InterruptedException {

        return userService.updateUser(user);
    }

    @DeleteMapping("/delete_user/{email}" )
    public String deleteUser(@PathVariable String email) throws ExecutionException, InterruptedException {

        return userService.deleteUser(email);
    }

}
