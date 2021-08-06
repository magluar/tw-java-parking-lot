package com.parkinglot;

public class NotEnoughPositionException extends RuntimeException{
    public String getMessage(){
        return "No available position.";
    }
}
