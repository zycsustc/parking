package org.parkingLot;

import java.util.List;

public record ParkingBoy(List<ParkingLot> parkingLotList) {

    Ticket park(Car car) {
        var maxEmptyNum = parkingLotList.stream().mapToInt(ParkingLot::emptyCapacity).reduce(0, Math::max);
        return parkingLotList.stream().filter(parkingLot -> parkingLot.emptyCapacity() == maxEmptyNum).toList().get(0).park(car);
    }
}
