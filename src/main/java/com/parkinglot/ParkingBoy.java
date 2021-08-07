package com.parkinglot;

public class ParkingBoy {
    public ParkingBoy(ParkingLot parkingLot) {
    }

    public ParkingTicket park(Car car) {
        return new ParkingTicket();
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return null;
    }
}
