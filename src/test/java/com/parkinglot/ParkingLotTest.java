package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    public void should_return_parking_ticket_when_park_given_parking_lot_and_car(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetching_the_car_given_parking_lot_and_parked_car(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car actualCar = parkingLot.fetch(parkingTicket);

        //then
        assertNotNull(actualCar);
    }

    @Test
    public void should_return_right_car_with_each_ticket_when_fetch_car_twice_given_parking_lot_and_two_parked_cars(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car aliceCar = new Car();
        Car bobCar = new Car();
        ParkingTicket aliceTicket = parkingLot.park(aliceCar);
        ParkingTicket bobTicket = parkingLot.park(bobCar);

        //when
        Car actualAliceCar = parkingLot.fetch(aliceTicket);
        Car actualBobCar = parkingLot.fetch(bobTicket);

        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);
    }

    @Test
    public void should_return_nothing_when_fetch_car_given_parking_lot_and_wrong_parking_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket wrongTicket = new ParkingTicket();

        //when
        Car actualCar = parkingLot.fetch(wrongTicket);

        //then
        assertNull(actualCar);
    }
}
