package com.BloodBank.Management.exception;

public class DonerNotFoundException extends RuntimeException{

    public DonerNotFoundException(Long id){
        super("Could not found Doner with id " +id);

    }



}
