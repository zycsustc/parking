package org.parkingLot;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {
    final ParkingLot parkingLot1 = new ParkingLot(1);
    final ParkingLot parkingLot2 = new ParkingLot(3);
    final ParkingLot parkingLot3 = new ParkingLot(3);
    final List<ParkingLot> parkingLotList = List.of(parkingLot1, parkingLot2, parkingLot3);
    @Test
    void should_return_ticket_and_park_by_order_when_park_given_parkingLots_have_same_empty_capacity() {
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();
        var ticket = parkingBoy.park(car);

        assertThat(ticket).isNotNull();
        assertThat(ticket).isInstanceOf(Ticket.class);
        assertThat(parkingLot1.contains(ticket)).isEqualTo(false);
        assertThat(parkingLot2.contains(ticket)).isEqualTo(true);
        assertThat(parkingLot3.contains(ticket)).isEqualTo(false);
    }
}
