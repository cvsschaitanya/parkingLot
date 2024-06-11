package models;

public class Vehicle {
    int id;
    VehicleType vehicleType;
    String color;

    public Slot getSlot() {
        return slot;
    }

    Slot slot;
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }


    public Vehicle(int id, VehicleType vehicleType, String color) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    public int getId() {
        return this.id;
    }
}
