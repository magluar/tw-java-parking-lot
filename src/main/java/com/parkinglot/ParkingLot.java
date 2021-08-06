package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private static final int DEFAULT_CAPACITY = 10;
    private Car car;
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();
    private final int capacity;

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (parkedPosition.size() == capacity){
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.car = car;

        parkedPosition.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (!isTicketRecognized(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }
        Car car = parkedPosition.get(parkingTicket);
        parkedPosition.remove(parkingTicket);
        return car;
    }

    public boolean isTicketRecognized(ParkingTicket parkingTicket){
        return parkedPosition.containsKey(parkingTicket);
    }


}
