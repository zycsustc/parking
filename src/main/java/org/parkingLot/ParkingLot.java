package org.parkingLot;

import org.parkingLot.exceptions.Constants;
import org.parkingLot.exceptions.InvalidTicketException;

import java.util.ArrayList;

public class ParkingLot {
    int parkingSpotNumber;
    ArrayList<Car> parkedCars = new ArrayList<>();

    public ParkingLot(int size) {
        parkingSpotNumber = size;
    }

    Ticket park(Car car) throws InvalidTicketException {
        if (parkedCars.size() < parkingSpotNumber) {
            parkedCars.add(car);
        } else {
            throw new InvalidTicketException(Constants.parkingLotFullErrMsg);
        }
        return new Ticket(car.licensePlateNumber);
    }
}
