package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingBoy {
    private Map<ParkingTicket, Car> parkedPosittion = new HashMap<>();
    private Car car;


    public ParkingBoy(ParkingLot parkingLot) {
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        this.car = car;

        parkedPosittion.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!isTicketRecognized(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }
        Car car = parkedPosittion.get(parkingTicket);
        parkedPosittion.remove(parkingTicket);
        return car;
    }

    public boolean isTicketRecognized(ParkingTicket parkingTicket){
        return parkedPosittion.containsKey(parkingTicket);
    }
}
