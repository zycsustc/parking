package org.parkingLot;

import org.parkingLot.constants.ExceptionConstants;
import org.parkingLot.exceptions.InvalidTicketException;
import org.parkingLot.exceptions.ParkingLotFullException;

import java.util.ArrayList;
import java.util.Objects;

public class ParkingLot {
    int parkingSpotNumber;
    private final ArrayList<Car> parkedCars = new ArrayList<>();

    public ParkingLot(int size) {
        parkingSpotNumber = size;
    }

    Ticket park(Car car) throws ParkingLotFullException {
        if (isFull()) {
            throw new ParkingLotFullException(ExceptionConstants.parkingLotFullErrMsg);
        } else {
            parkedCars.add(car);
        }
        return new Ticket(car.licensePlateNumber);
    }

    Car pick(Ticket ticket) throws InvalidTicketException {
        if (ticket.licensePlateNumber.length() != 6) {
            throw new InvalidTicketException(ExceptionConstants.illegalLicensePlateNumberErrMsg);
        }
        for (Car parkedCar : parkedCars) {
            if (Objects.equals(parkedCar.licensePlateNumber, ticket.licensePlateNumber)) {
                return parkedCar;
            }
        }
        return null;
    }

    public Boolean isFull() {
        return parkedCars.size() >= parkingSpotNumber;
    }
}
