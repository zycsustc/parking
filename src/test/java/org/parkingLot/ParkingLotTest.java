package org.parkingLot;

import org.junit.jupiter.api.Test;
import org.parkingLot.exceptions.InvalidTicketException;
import org.parkingLot.exceptions.ParkingLotFullException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_non_full_filled_parkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car();

        var ticket = parkingLot.park(car);

        assertThat(ticket).isNotNull();
        assertThat(ticket).isInstanceOf(Ticket.class);
    }

    @Test
    void should_throw_ParkingLotFullException_when_park_given_parkingLot_isFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();

        parkingLot.park(car1);

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car2));
    }

    @Test
    void should_pick_car_successfully_when_pick_given_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car();
        var ticket = parkingLot.park(car);

        var pickedCar = parkingLot.pick(ticket);

        assertThat(car).isEqualTo(pickedCar);
    }

    @Test
    void should_throw_InvalidTicketException_when_pick_given_invalid_ticket() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car();
        var validTicket = parkingLot.park(car);
        var invalidTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> parkingLot.pick(invalidTicket));
        assertThat(car).isEqualTo(parkingLot.pick(validTicket));
    }

    @Test
    void should_throw_InvalidTicketException_when_pick_given_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car = new Car();
        var ticket1 = parkingLot.park(car);
        parkingLot.pick(ticket1);
        var ticket2 = parkingLot.park(car);

        assertThrows(InvalidTicketException.class, () -> parkingLot.pick(ticket1));
        assertThat(car).isEqualTo(parkingLot.pick(ticket2));
    }
}
