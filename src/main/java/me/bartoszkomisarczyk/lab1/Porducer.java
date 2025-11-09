package me.bartoszkomisarczyk.lab1;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Porducer implements Runnable {

    private final SharedResource resource;
    private final int min, max;
    private final static Random rand = new Random();

    public Porducer(SharedResource res, int min, int max) {
        this.resource = res;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int i = rand.nextInt(max - min + 1) + min;
            try {
                resource.setNumber(i);
            } catch (InterruptedException e) {
                IO.println(Thread.currentThread().getName() + " interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
}
