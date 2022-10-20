package org.parkingLot;

import org.parkingLot.constants.BusinessConstants;
import org.parkingLot.exceptions.InvalidTicketException;
import org.parkingLot.exceptions.ParkingLotFullException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Parking Lot system, please init by enter the size of parking lot:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputSize = br.readLine();
        ParkingLot parkingLot = new ParkingLot(Integer.parseInt(inputSize));
        boolean running = true;
        while (running) {
            System.out.println("Please enter your command: (park/pick/exit)");
            String input = br.readLine();
            if (Objects.equals(input, "park")) {
                System.out.println("Please enter your car license plate number:");
                input = br.readLine();
                if (input.length() != BusinessConstants.licensePlateNumberLength) {
                    System.out.println("License plate number format is wrong.");
                } else {
                    try {
                        var ticket = parkingLot.park(new Car(input));
                        System.out.println("Your car is parked.");
                        System.out.println("Park time: "+ticket.parkTime+"\nLicense plate number: "+ticket.licensePlateNumber);
                    } catch (ParkingLotFullException exception) {
                        System.out.println(exception.getMessage());
                    }
                }
            } else if (Objects.equals(input, "pick")) {
                System.out.println("Please enter your ticket park time:");
                input = br.readLine();
                String time = input;
                System.out.println("Please enter your car license plate number:");
                input = br.readLine();
                String number = input;
                Ticket ticket = new Ticket(number, time);
                try {
                    var car = parkingLot.pick(ticket);
                    System.out.println("Here is your car: "+car.licensePlateNumber);
                } catch (InvalidTicketException exception) {
                    System.out.println(exception.getMessage());
                }
            } else if (Objects.equals(input, "exit")) {
                running = false;
            } else {
                System.out.println("This is not supported now.");
            }
        }
    }
}