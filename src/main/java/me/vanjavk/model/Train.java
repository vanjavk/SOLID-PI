package me.vanjavk.model;

import me.vanjavk.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//test
public class Train {
    private String locomotive;
    private int maxCapacity;
    private List<Class<? extends Vehicle>> allowedVehicles;
    private List<List<Vehicle>> train = new ArrayList<>();

    public Train(String locomotive, int maxCapacity, Class<? extends Vehicle>... allowedVehicles) {
        this.locomotive = locomotive;
        this.maxCapacity = maxCapacity;
        this.allowedVehicles = Arrays.asList(allowedVehicles);
        train.add(new ArrayList<>());
    }

    private boolean allowed(Vehicle vehicle) {
        return allowedVehicles.stream().anyMatch(allowedVehicle -> vehicle.getClass().equals(allowedVehicle));
    }

    public boolean add(Vehicle vehicle) {
        if (!allowed(vehicle)) return false;

        List<Vehicle> wagon = train.get(train.size() - 1);
        if (wagon.size() >= maxCapacity) {
            train.add(new ArrayList<>() {
                {
                    add(vehicle);
                }
            });
        } else {
            wagon.add(vehicle);
        }
        return true;
    }

    @Override
    public String toString() {
        return locomotive + train;
    }
}
