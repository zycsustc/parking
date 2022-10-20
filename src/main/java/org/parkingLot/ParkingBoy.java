package org.parkingLot;

import org.parkingLot.exceptions.InvalidTicketException;

import java.util.List;

public record ParkingBoy(List<ParkingLot> parkingLotList) {

    Ticket park(Car car) {
        var maxEmptyNum = parkingLotList.stream().mapToInt(ParkingLot::emptyCapacity).reduce(0, Math::max);
        return parkingLotList.stream().filter(parkingLot -> parkingLot.emptyCapacity() == maxEmptyNum).toList().get(0).park(car);
    }

    Car pick(Ticket ticket) {
        var possibleParkingLot = parkingLotList.stream().filter(parkingLot -> parkingLot.contains(ticket)).findFirst();
        if(possibleParkingLot.isPresent()){
            return possibleParkingLot.get().pick(ticket);
        }
        throw new InvalidTicketException();
    }
}
