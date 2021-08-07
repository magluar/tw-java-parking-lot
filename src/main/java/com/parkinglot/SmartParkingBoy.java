package com.parkinglot;

import java.util.List;
import java.util.Objects;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithMostAvailablePosition = null;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLotWithMostAvailablePosition == null || parkingLot.getAvailablePosition() > parkingLotWithMostAvailablePosition.getAvailablePosition()){
                parkingLotWithMostAvailablePosition = parkingLot;
            }
        }
        return Objects.requireNonNull(parkingLotWithMostAvailablePosition).park(car);
    }
}
