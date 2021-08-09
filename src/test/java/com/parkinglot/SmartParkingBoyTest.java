package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    @Test
    public void should_park_to_first_parking_lot_when_park_the_car_given_two_parking_lot_with_first_parking_more_available_position_and_a_car_to_a_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.park(new Car());
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        Car actualCar = parkingLot1.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    public void should_park_to_second_parking_lot_when_park_the_car_given_two_parking_lot_with_second_parking_lot_has_more_available_position_and_a_car_to_a_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        Car actualCar = parkingLot2.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_right_car_with_each_ticket_when_fetch_car_twice_given_two_parking_lots_with_both_parked_car_and_two_parking_ticket_and_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        Car aliceCar = new Car();
        ParkingTicket aliceTicket = parkingLot1.park(aliceCar);
        ParkingLot parkingLot2 = new ParkingLot(1);
        Car bobCar = new Car();
        ParkingTicket bobTicket = parkingLot2.park(bobCar);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Car actualAliceCar = smartParkingBoy.fetch(aliceTicket);
        Car actualBobCar = smartParkingBoy.fetch(bobTicket);

        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);
    }

    @Test
    public void should_return_exception_with_error_message_when_fetch_the_car_given_unrecognized_tickets_and_two_parking_lots_to_a_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_with_error_message_when_fetch_the_car_given_used_tickets_and_two_parking_lots_to_a_parking_boy() {
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingTicket usedTicket = parkingLot2.park(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        smartParkingBoy.fetch(usedTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(usedTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_with_error_message_when_park_the_car_given_two_parking_lots_both_without_any_position_and_a_car_to_a_parking_boy(){
        //when
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot1.park(new Car());
        parkingLot2.park(new Car());
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();

        //when
        Exception exception = assertThrows(NotEnoughPositionException.class, () -> smartParkingBoy.park(car));

        //then
        assertEquals("No available position.", exception.getMessage());

    }
}
