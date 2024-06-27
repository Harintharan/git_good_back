package com.BloodBank.Management.controller;




import com.BloodBank.Management.entity.User;

import com.BloodBank.Management.exception.UserNotFoundException;


import com.BloodBank.Management.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")

public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserbyId(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }



    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id )
    {

        return userRepository.findById (id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());

                    return userRepository.save(user);
                }) .orElseThrow(()->new UserNotFoundException(id));
    }



    @DeleteMapping("/user/{id}")
    String  deleteUser(@PathVariable Long id)
    {
        if (!userRepository.existsById(id))
        {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with identy_number"+id+"has been deleted success." ;




    }





}
