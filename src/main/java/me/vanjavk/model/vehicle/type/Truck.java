package me.vanjavk.model.vehicle.type;

import me.vanjavk.model.vehicle.Vehicle;

public class Truck extends Vehicle {
    @Override
    public int park(){
        return 90;
    }

    @Override
    public String toString() {
//        return "TRK";
        return "🚚";
    }
}
