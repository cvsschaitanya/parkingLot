package controllers;

import models.Vehicle;

public class TicketGenerator {
    int id;

    public int getId() {
        return id;
    }

    TicketingSystem ticketingSystem;

    public TicketGenerator(int id, TicketingSystem ticketingSystem) {
        this.ticketingSystem = ticketingSystem;
        this.id = id;
    }

    public void getTicket(Vehicle vehicle) {
        ticketingSystem.registerVehicleByGenerator(vehicle, this);

    }
}
