package org.parkingLot.exceptions;

public class InvalidTicketException extends Exception {
    public InvalidTicketException(String msg){
        super(msg);
    }
}
