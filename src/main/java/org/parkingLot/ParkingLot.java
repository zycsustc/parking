package org.parkingLot;

import org.parkingLot.constants.ExceptionConstants;
import org.parkingLot.exceptions.InvalidTicketException;

import java.util.ArrayList;
import java.util.Objects;

public class ParkingLot {
    int parkingSpotNumber;
    private final ArrayList<Car> parkedCars = new ArrayList<>();

    public ParkingLot(int size) {
        parkingSpotNumber = size;
    }

    Ticket park(Car car) throws InvalidTicketException {
        if (isFull()) {
            throw new InvalidTicketException(ExceptionConstants.parkingLotFullErrMsg);
        } else {
            parkedCars.add(car);
        }
        return new Ticket(car.licensePlateNumber);
    }

    Car pick(Ticket ticket) {
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
