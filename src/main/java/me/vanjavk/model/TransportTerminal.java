package me.vanjavk.model;

import me.vanjavk.interfaces.Money;
import me.vanjavk.model.vehicle.Vehicle;
import me.vanjavk.model.vehicle.type.Bus;
import me.vanjavk.model.vehicle.type.Car;
import me.vanjavk.model.vehicle.type.Truck;
import me.vanjavk.model.vehicle.type.Van;

import java.util.*;

public class TransportTerminal implements Money {
    private Queue<Vehicle> queue = new LinkedList<>();
    private List<Train> trains = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();
    private double money = 0;

    public TransportTerminal(int carAmount, int vanAmount, int busAmount, int truckAmount) {

        for (int i = 0; i < carAmount; i++) {
            queue.add(new Car());
        }
        for (int i = 0; i < vanAmount; i++) {
            queue.add(new Van());
        }
        for (int i = 0; i < busAmount; i++) {
            queue.add(new Bus());
        }
        for (int i = 0; i < truckAmount; i++) {
            queue.add(new Truck());
        }
        Collections.shuffle(Arrays.asList(queue.toArray()));

        workers.add(new Worker(0.1, "😊"));//😊
        workers.add(new Worker(0.11, "🙂"));//🙂
        trains.add(new Train("🚄", 8, Car.class, Van.class));//🚄
        trains.add(new Train("🚅", 6, Bus.class, Truck.class));//🚅
    }

    private void tick() {//game logic
        workers.forEach(worker -> {
            Vehicle vehicle = worker.getCurrentVehicle();
            if (worker.getStatus() == Worker.Status.PARKING) {
                worker.setStatus(Worker.Status.WAITING);
                return;
            }
            if (vehicle == null) {
                if (queue.size() == 0) {
                    return;
                }
                worker.setCurrentVehicle(queue.remove());
                worker.setStatus(Worker.Status.WORKING);
            } else {
                if (vehicle.refill()) {
                    worker.setStatus(Worker.Status.FUELING);
                    return;
                }
                worker.setStatus(Worker.Status.PARKING);
                trains.stream().anyMatch(train -> train.add(vehicle));
                addMoney(worker.addMoney(vehicle.park()));
                worker.leaveVehicle();
            }
        });
        render();
    }

    private void render() {
        for (int i = 0; i < 2; i++) {
            System.out.println("===============================================================================");
        }
        System.out.println(queue);
        workers.forEach(System.out::println);
        System.out.println("Total earnings: " + String.format("%.2f", money) + "kn");
        trains.forEach(System.out::println);
    }

    private boolean working() {
        return !queue.isEmpty() || workers.stream().anyMatch(worker -> !worker.getStatus().equals(Worker.Status.WAITING));
    }

    public void play(int tps) throws InterruptedException {
        render();
        double next_render_tick = System.currentTimeMillis();

        while (working()) {
            while (System.currentTimeMillis() > next_render_tick) {
                tick();
                next_render_tick += 1000 / tps;
            }
            Thread.sleep(1); //da ne pocucla cijeli cpu%
        }
    }


    @Override
    public double balance() {
        return 0;
    }

    @Override
    public double addMoney(double money) {
        this.money += money;
        return money;
    }
}
