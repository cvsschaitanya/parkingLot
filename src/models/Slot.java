package models;

public class Slot {
    int id;
    VehicleType slotType;
    int vehicleCount;

    public Slot(int id) {
        this.id = id;
        this.slotType = VehicleType.NA;
        this.vehicleCount = 0;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public void setSlotType(VehicleType slotType) {
        this.slotType = slotType;
    }

    public int getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }
}
