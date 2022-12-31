package com.example.springbootmongodb.services.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String msm){
        super(msm);
    }
}
