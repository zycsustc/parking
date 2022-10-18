package org.parkingLot;

import java.util.ArrayList;

public class ParkingLot {
    int parkingSpotNumber;
    ArrayList<Car> parkedCars = new ArrayList<>();
    public ParkingLot(int size){
        parkingSpotNumber = size;
    }

    Ticket park(Car car) {
        parkedCars.add(car);
        return new Ticket(car.licensePlateNumber);
    }
}
