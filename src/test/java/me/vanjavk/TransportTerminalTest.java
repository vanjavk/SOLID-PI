package me.vanjavk;

import me.vanjavk.model.Train;
import me.vanjavk.model.TransportTerminal;
import me.vanjavk.model.Worker;
import me.vanjavk.model.vehicle.Vehicle;
import me.vanjavk.model.vehicle.type.Bus;
import me.vanjavk.model.vehicle.type.Car;
import me.vanjavk.singleton.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransportTerminalTest {

    @Test
    public void shouldReturnNoException() {
        TransportTerminal transportTerminal = new TransportTerminal(10, 10, 10, 10);
        try {
            transportTerminal.play(1000);
        } catch (InterruptedException e) {
            Assertions.fail();
        }
    }

    @Test
    public void shouldBeSameInstanceSinceSingleton() {
        Random Random1 = Random.getInstance();
        Random Random2 = Random.getInstance();
        Assertions.assertTrue(Random1 == Random2);
    }

    @Test
    public void shouldReturnCorrectSalary() {
        Worker worker = new Worker(0.2,"Smiley");
        worker.addMoney(new Bus().park());
        Assertions.assertTrue(worker.balance()==0.2*80);
    }

    @Test
    public void shouldAllowBusButNotCar() {
            Train train = new Train("ChoCho" ,5, Bus.class);
        Assertions.assertFalse(train.add(new Car()));
        Assertions.assertTrue(train.add(new Bus()));
    }


}
