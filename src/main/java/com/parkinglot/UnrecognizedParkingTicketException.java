package com.parkinglot;

public class UnrecognizedParkingTicketException extends RuntimeException {
    public String getMessage(){
        return "Unrecognized parking ticket.";
    }
}
