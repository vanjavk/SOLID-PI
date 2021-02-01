package me.vanjavk.model;

import me.vanjavk.interfaces.Money;
import me.vanjavk.model.vehicle.Vehicle;

public class Worker implements Money {
    private double salary;
    private String face;
    private Vehicle currentVehicle;
    private double money = 0;
    private Status status = Status.WAITING;

    public Worker(double salary, String face) {
        this.salary = salary;
        this.face = face;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    public void leaveVehicle() {
        this.currentVehicle = null;
    }

    public String smile() {
        return face;
    }

    @Override
    public double balance() {
        return money;
    }

    @Override
    public double addMoney(double money) {
        this.money += money*salary;
        return money;
    }

    @Override
    public String toString() {
        return smile() + " " + status + " earnings: " + String.format("%.2f", balance()) + "kn" + System.lineSeparator() +
                (currentVehicle == null ? "" : (currentVehicle + " " + currentVehicle.getFuel() + "/100") + System.lineSeparator() +
                (status.equals(Status.FUELING) ? currentVehicle.getEngineType() : ""));
    }

    public enum Status {

        WAITING("💤"),//
        WORKING("💪"),
        FUELING("🏭"),
        PARKING("🅿");//

        private final String status;

        Status(String status) {
            this.status = status;
        }

        public String toString() {
            return status;
        }
    }
}
