package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public Car car;
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        this.car = car;

        parkedPosition.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkedPosition.get(parkingTicket);
    }
}
