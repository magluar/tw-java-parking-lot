package com.parkinglot;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{


    public SuperSmartParkingBoy(List<ParkingLot> parkingLots){
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithLargerPositionRateAvailable = null;
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.getAvailablePosition() > 0) {
                if (parkingLotWithLargerPositionRateAvailable == null || parkingLot.getLargerAvailablePositionRate() >
                        parkingLotWithLargerPositionRateAvailable.getLargerAvailablePositionRate()) {
                    parkingLotWithLargerPositionRateAvailable = parkingLot;
                }
            }
        }
        if(parkingLotWithLargerPositionRateAvailable == null){
            throw new NotEnoughPositionException();
        }
        return parkingLotWithLargerPositionRateAvailable.park(car);
    }
}
