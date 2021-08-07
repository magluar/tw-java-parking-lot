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
        for (ParkingLot parkingLot : parkingLots) {
            try{
                return parkingLot.park(car);
            }
            catch(NotEnoughPositionException e){

            }
        }
        throw new NotEnoughPositionException();
    }

    public Car fetch(ParkingTicket parkingTicket){
        for (ParkingLot parkingLot : parkingLots) {
            try{
                if (!parkingLot.isTicketRecognized(parkingTicket)){
                    return parkingLot.fetch(parkingTicket);
                }
            }
            catch(UnrecognizedParkingTicketException ignored){

            }
        }
        throw new UnrecognizedParkingTicketException();
    }
}
