package org.parkingLot;

import org.junit.jupiter.api.Test;
import org.parkingLot.exceptions.Constants;
import org.parkingLot.exceptions.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    final String mockLicensePlateNumber1 = "ABC012";
    final String mockLicensePlateNumber2 = "ABC013";

    @Test
    void should_park_successfully_given_nonEmpty_parkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10);
        Car car = new Car(mockLicensePlateNumber1);

        var ticket = parkingLot.park(car);

        assertThat(ticket.licensePlateNumber).isEqualTo(mockLicensePlateNumber1);
    }

    @Test
    void should_park_failed_given_full_parkingLot() throws Exception {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car(mockLicensePlateNumber1);
        Car car2 = new Car(mockLicensePlateNumber2);

        parkingLot.park(car1);

        Exception exception = assertThrows(InvalidTicketException.class, () -> parkingLot.park(car2));
        assertThat(exception.getMessage()).isEqualTo(Constants.parkingLotFullErrMsg);
    }
}
