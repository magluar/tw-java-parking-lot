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
        return parkedPosittion.get(parkingTicket);
//        return new Car();
    }
}
