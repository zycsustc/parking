package org.parkingLot;

import org.junit.jupiter.api.Test;
import org.parkingLot.exceptions.InvalidTicketException;
import org.parkingLot.exceptions.ParkingLotFullException;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_and_park_by_order_when_park_given_parkingLots_have_same_empty_capacity() {
        final ParkingLot parkingLot1 = new ParkingLot(3);
        final ParkingLot parkingLot2 = new ParkingLot(3);
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));
        Car car = new Car();
        var ticket = parkingBoy.park(car);

        assertThat(ticket).isNotNull();
        assertThat(ticket).isInstanceOf(Ticket.class);
        assertThat(parkingLot1.contains(ticket)).isEqualTo(true);
        assertThat(parkingLot2.contains(ticket)).isEqualTo(false);
    }

    @Test
    void should_return_ticket_and_park_to_most_empty_parkingLot_when_park_given_parkingLots_with_different_empty_capacity(){
        final ParkingLot parkingLot1 = new ParkingLot(1);
        final ParkingLot parkingLot2 = new ParkingLot(3);
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));
        Car car = new Car();

        var ticket = parkingBoy.park(car);
        assertThat(ticket).isNotNull();
        assertThat(ticket).isInstanceOf(Ticket.class);
        assertThat(parkingLot1.contains(ticket)).isEqualTo(false);
        assertThat(parkingLot2.contains(ticket)).isEqualTo(true);
    }

    @Test
    void should_throw_ParkingLotFullException_when_park_given_all_parkingLots_are_full(){
        final ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot));
        parkingBoy.park(new Car());
        Car car = new Car();

        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(car));
    }

    @Test
    void should_return_car_when_pick_given_valid_ticket() {
        final ParkingLot parkingLot1 = new ParkingLot(2);
        final ParkingLot parkingLot2 = new ParkingLot(3);
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        Car car = new Car();
        var ticket = parkingBoy.park(car);

        assertEquals(car, parkingBoy.pick(ticket));
    }

    @Test
    void should_throw_invalidTicket_when_pick_given_invalid_ticket() {
        final ParkingLot parkingLot1 = new ParkingLot(2);
        final ParkingLot parkingLot2 = new ParkingLot(3);
        ParkingBoy parkingBoy = new ParkingBoy(List.of(parkingLot1, parkingLot2));
        parkingBoy.park(new Car());
        Ticket fakeTicket = new Ticket();

        assertThrows(InvalidTicketException.class, () -> parkingBoy.pick(fakeTicket));
    }
}
