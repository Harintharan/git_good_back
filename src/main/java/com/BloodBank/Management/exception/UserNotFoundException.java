package com.BloodBank.Management.exception;

public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException(Long id){
        super("Could not found User with id " +id);

    }

}
