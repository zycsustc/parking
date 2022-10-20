package org.parkingLot;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ParkingBoy {
    private final List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    Ticket park(Car car) {
        var maxEmptyNum = parkingLotList.stream().mapToInt(ParkingLot::emptyCapacity).reduce(0, Math::max);
        return parkingLotList.stream().filter(parkingLot -> parkingLot.emptyCapacity() == maxEmptyNum).toList().get(0).park(car);
    }
}
