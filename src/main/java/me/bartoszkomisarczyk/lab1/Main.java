package me.bartoszkomisarczyk.lab1;

public class Main {
    static void main(String... args) throws InterruptedException {

        SharedResource resource = new SharedResource();
        Porducer w1 = new Porducer(resource, 21, 37);
        Porducer w2 = new Porducer(resource, 1337, 4200);
        Consumer r = new Consumer(resource);

        Thread[] workers = new Thread[]{
                new Thread(w1, "122448_Producer_1"),
                new Thread(w2, "122448_Producer_2"),
                new Thread(r, "122448_Consumer_1"),
        };

        Thread monitoring = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                for (Thread t : workers) {
                    IO.print("-- " + t.getName() + " --");
                    IO.print(" { state: " + t.getState());
                    IO.print(" | is interrupted: " + t.isInterrupted() + " }");
                    IO.println();
                }
                IO.println("-- Shared resource value: " + resource.peekNumber());
                IO.println("======================");
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "122448_Monitoring_1");
        monitoring.setDaemon(true);

        for (Thread t : workers) {
            t.start();
        }

        monitoring.start();
        Thread.sleep(30_000);
        for (Thread t : workers) {
            t.interrupt();
        }
    }
}
