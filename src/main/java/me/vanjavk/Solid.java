package me.vanjavk;

import me.vanjavk.model.TransportTerminal;
import me.vanjavk.singleton.Random;

public class Solid {
    public static void main(String[] args) throws InterruptedException {
        TransportTerminal transportTerminal = new TransportTerminal(Random.getInstance().nextInt(10) + 4, Random.getInstance().nextInt(10) + 4, Random.getInstance().nextInt(6) + 3, Random.getInstance().nextInt(6) + 3);

        transportTerminal.play(2);

        System.out.println("DONE!");

    }
}

