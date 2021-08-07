package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    public void should_return_parking_ticket_when_park_given_parking_lot_and_car_to_parking_boy(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetching_the_car_given_parking_lot_and_parked_car_to_a_parking_boy(){
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car actualCar = parkingBoy.fetch(parkingTicket);

        //then
        assertNotNull(actualCar);
    }

    @Test
    public void should_return_right_car_with_each_ticket_when_fetch_car_twice_given_parking_lot_and_two_parked_cars_to_a_parking_boy(){
        ParkingLot parkingLot = new ParkingLot();
        Car aliceCar = new Car();
        Car bobCar = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket aliceTicket = parkingBoy.park(aliceCar);
        ParkingTicket bobTicket = parkingBoy.park(bobCar);

        //when
        Car actualAliceCar = parkingBoy.fetch(aliceTicket);
        Car actualBobCar = parkingBoy.fetch(bobTicket);

        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);
    }

    @Test
    public void should_throw_exception_with_error_message_when_fetch_given_parking_lot_and_an_unrecognized_parking_ticket_to_a_parking_boy(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
