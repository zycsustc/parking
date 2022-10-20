package org.parkingLot;

import org.parkingLot.exceptions.InvalidTicketException;
import org.parkingLot.exceptions.ParkingLotFullException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    final int capacity;
    private final Map<Ticket, Car> parkedCarMap = new HashMap<>();

    public ParkingLot(int size) {
        capacity = size;
    }

    Ticket park(Car car) throws ParkingLotFullException {
        if (isFull()) {
            throw new ParkingLotFullException();
        }
        var ticket = new Ticket();
        parkedCarMap.put(ticket, car);
        return ticket;
    }

    Car pick(Ticket ticket) throws InvalidTicketException {
        if (!parkedCarMap.containsKey(ticket)) {
            throw new InvalidTicketException();
        }
        return parkedCarMap.remove(ticket);
    }

    public boolean isFull() {
        return parkedCarMap.size() >= capacity;
    }

    public boolean contains(Ticket ticket) {
        return parkedCarMap.containsKey(ticket);
    }

    public int emptyCapacity() {
        return capacity - parkedCarMap.size();
    }
}
