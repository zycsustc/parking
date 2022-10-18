package org.parkingLot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingLotTest {
    final String mockLicensePlateNumber = "ABC012";

    @Test
    void should_park_successfully_given_nonEmpty_parkingLot() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car(mockLicensePlateNumber);
        var ticket = parkingLot.park(car);
        assertThat(ticket.licensePlateNumber).isEqualTo(mockLicensePlateNumber);
    }
}
