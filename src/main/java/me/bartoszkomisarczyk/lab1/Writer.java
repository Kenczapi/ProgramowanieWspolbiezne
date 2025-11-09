package me.bartoszkomisarczyk.lab1;

import java.util.Random;

public class Writer implements Runnable {

    private final SharedResource resource;
    private final int min, max;
    private final static Random rand = new Random();

    public Writer(SharedResource res, int min, int max) {
        this.resource = res;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //IO.println("Writer working...");
            int i = rand.nextInt(max - min + 1) + min;
            try {
                resource.setNumber(i);
                //IO.println(Thread.currentThread().getName() + " just wrote!");
            } catch (InterruptedException e) {
                IO.println("Writer interrupted -");
                Thread.currentThread().interrupt();
            }
        }
    }
}
