package controllers;

import models.Slot;
import models.Vehicle;
import models.VehicleType;

import java.util.HashMap;

public class TicketingSystem {
    private static final int P = 4;
    private static final int NUMBER_OF_ENTRY_POINTS = 4;
    private static final int NUMBER_OF_SLOTS = NUMBER_OF_ENTRY_POINTS * P;
    private static final int SLOT_SIZE = 4;

    private static TicketingSystem singletonInstance;

    public TicketGenerator[] getTicketGenerators() {
        return ticketGenerators;
    }

    TicketGenerator[] ticketGenerators;
    HashMap<Integer, Vehicle> vehicleMap;
    Slot[] slots;

    public TicketingSystem() {
        this.ticketGenerators = new TicketGenerator[NUMBER_OF_ENTRY_POINTS];
        for (int i = 0; i < NUMBER_OF_ENTRY_POINTS; i++) {
            this.ticketGenerators[i] = new TicketGenerator(i * P, this);
        }
        this.slots = new Slot[NUMBER_OF_SLOTS];
        for (int i = 0; i < NUMBER_OF_SLOTS; i++) {
            this.slots[i] = new Slot(i);
        }
        vehicleMap = new HashMap<>();
    }

    public static synchronized TicketingSystem getInstance() {
        if (singletonInstance == null)
            singletonInstance = new TicketingSystem();

        return singletonInstance;
    }


    public synchronized void registerVehicleByGenerator(Vehicle vehicle, TicketGenerator ticketGenerator) {
        vehicleMap.put(vehicle.getId(), vehicle);

        int offset = 0;
        do {
            if (tryTheSlot(this.slots[(ticketGenerator.getId() + offset) % NUMBER_OF_SLOTS], vehicle))
                break;

            if (tryTheSlot(this.slots[(ticketGenerator.getId() - offset) % NUMBER_OF_SLOTS], vehicle))
                break;

            offset++;
        } while (offset <= NUMBER_OF_SLOTS / 2);

        if(vehicle.getSlot() == null) {
            System.out.println("No slot available!!");
        }

    }

    private boolean tryTheSlot(Slot candidateSlot, Vehicle vehicle) {
        if (candidateSlot.getSlotType() != VehicleType.NA && candidateSlot.getSlotType() != vehicle.getVehicleType())
            return false;

        if (!canFitVehicleInSlot(vehicle, candidateSlot))
            return false;

        candidateSlot.setSlotType(vehicle.getVehicleType());
        candidateSlot.setVehicleCount(candidateSlot.getVehicleCount() + 1);
        vehicle.setSlot(candidateSlot);
        return true;
    }

    private boolean canFitVehicleInSlot(Vehicle vehicle, Slot slot) {
        int vehicleSize = 0;
        switch (vehicle.getVehicleType()) {
            case BIKE -> vehicleSize = 1;
            case CAR -> vehicleSize = 2;
            case TRUCK -> vehicleSize = 4;
        }
        return vehicleSize * (1 + slot.getVehicleCount()) < SLOT_SIZE;
    }
}
