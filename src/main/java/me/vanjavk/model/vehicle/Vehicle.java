package me.vanjavk.model.vehicle;

import me.vanjavk.interfaces.Parkable;
import me.vanjavk.interfaces.Refillable;
import me.vanjavk.singleton.Random;


public abstract class Vehicle implements Parkable, Refillable {

    private int fuel = Random.getInstance().nextInt(100+1);
    private EngineType engineType = Random.getInstance().nextInt(2) == 1 ? EngineType.ELECTRIC : EngineType.TRADITIONAL;

    @Override
    public Boolean refill() {
        if (fuel < 10) {
            fuel++;
            return true;
        }
        return false;
    }

    public int getFuel() {
        return fuel;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public enum EngineType {

        ELECTRIC("🔌"),//🔌
        TRADITIONAL("⛽");//⛽

        private final String type;

        EngineType(String type) {
            this.type = type;
        }

        public String toString() {
            return type;
        }
    }
}


