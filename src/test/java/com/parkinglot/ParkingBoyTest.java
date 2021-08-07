package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void should_throw_exception_with_error_message_when_fetch_given_parking_lot_and_used_parking_ticket_to_a_parking_boy(){
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        parkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_exception_with_message_when_parking_without_any_position_given_car_and_parking_lot_to_a_parking_boy(){
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingBoy.park(new Car());
        Car car = new Car();
        //when
        Exception exception = assertThrows(NotEnoughPositionException.class, () -> parkingBoy.park(car));

        //then
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_park_to_first_parking_lot_when_park_the_car_given_two_parking_lot_with_available_position_and_a_car_to_a_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        Car actualCar = parkingLot1.fetch(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    public void should_park_to_second_parking_lot_when_park_the_car_given_two_parking_lot_with_only_second_parking_lot_with_available_position_and_a_car_to_a_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

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
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Car actualAliceCar = parkingBoy.fetch(aliceTicket);
        Car actualBobCar = parkingBoy.fetch(bobTicket);

        //then
        assertEquals(aliceCar, actualAliceCar);
        assertEquals(bobCar, actualBobCar);
    }

    //Given a standard parking boy, who manage two parking lots, and an unrecognized ticket,
    // When fetch the car,
    // Then return nothing with error message "Unrecognized parking ticket.
    @Test
    public void should_return_exception_with_error_message_when_fetch_the_car_given_unrecognized_tickets_and_two_parking_lots_to_a_parking_boy(){
        //given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
