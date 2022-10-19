package org.parkingLot;

import org.parkingLot.constants.BusinessConstants;
import org.parkingLot.constants.ExceptionConstants;
import org.parkingLot.exceptions.InvalidTicketException;
import org.parkingLot.exceptions.ParkingLotFullException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class ParkingLot {
    int parkingSpotNumber;
    private final ArrayList<Car> parkedCars = new ArrayList<>();
    private final ArrayList<String> validParkTimes = new ArrayList<>();

    public ParkingLot(int size) {
        parkingSpotNumber = size;
    }

    Ticket park(Car car) throws ParkingLotFullException {
        if (isFull()) {
            throw new ParkingLotFullException(ExceptionConstants.parkingLotFullErrMsg);
        }
        parkedCars.add(car);
        return generateTicket(car.licensePlateNumber);
    }

    Car pick(Ticket ticket) throws InvalidTicketException {
        if (isTicketIllegal(ticket)) {
            throw new InvalidTicketException(ExceptionConstants.illegalLicensePlateNumberErrMsg);
        }
        if (!validParkTimes.contains(ticket.parkTime)) {
            throw new InvalidTicketException(ExceptionConstants.invalidTicketDefaultErrMsg);
        }
        for (Car parkedCar : parkedCars) {
            if (Objects.equals(parkedCar.licensePlateNumber, ticket.licensePlateNumber)) {
                parkedCars.remove(parkedCar);
                validParkTimes.remove(ticket.parkTime);
                return parkedCar;
            }
        }
        throw new InvalidTicketException(ExceptionConstants.carNotInParkingLotErrMsg);
    }

    public Boolean isFull() {
        return parkedCars.size() >= parkingSpotNumber;
    }

    private Boolean isTicketIllegal(Ticket ticket) {
        return ticket.licensePlateNumber.length() != BusinessConstants.licensePlateNumberLength;
    }

    private Ticket generateTicket(String licensePlateNumber) {
        final String time = Instant.now().toString();
        var ticket = new Ticket(licensePlateNumber, time);
        validParkTimes.add(time);
        return ticket;
    }
}
