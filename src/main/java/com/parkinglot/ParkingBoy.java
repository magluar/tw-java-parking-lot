package com.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot) {
//        this(Collections.singletonList(parkingLot));
        this(new ArrayList<ParkingLot>(){{
            add(parkingLot);
        }});
    }

    public ParkingBoy(List<ParkingLot> parkingLots){
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        return parkingLots.get(0).park(car);
    }

    public Car fetch(ParkingTicket parkingTicket){
        return parkingLots.get(0).fetch(parkingTicket);
    }
}
