package main;

import controllers.TicketingSystem;
import models.Vehicle;
import models.VehicleType;

public class Main {
    public static void main(String[] args) {
        TicketingSystem ticketingSystem = TicketingSystem.getInstance();

        ticketingSystem.getTicketGenerators()[0].getTicket(
                new Vehicle(1, VehicleType.CAR, "gray")
        );

    }
}
