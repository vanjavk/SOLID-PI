package me.vanjavk.model.vehicle.type;

import me.vanjavk.model.vehicle.Vehicle;

public class Car extends Vehicle {
    @Override
    public int park() {
        return 50;
    }

    @Override
    public String toString() {
//        return "CAR";
        return "🚗";
    }
}
