package me.vanjavk.model.vehicle.type;

import me.vanjavk.model.vehicle.Vehicle;

public class Bus extends Vehicle {
    @Override
    public int park() {
        return 80;
    }

    @Override
    public String toString() {
//        return "BUS";
        return "🚌";
    }
}
