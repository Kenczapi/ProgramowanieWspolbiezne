package me.bartoszkomisarczyk.lab1;

public class Main {
    static void main(String... args) throws InterruptedException {

        SharedResource resource = new SharedResource();
        Writer w1 = new Writer(resource, 21, 37);
        Writer w2 = new Writer(resource, 1337, 4200);
        Reader r = new Reader(resource);

        Thread[] workers = new Thread[]{
                new Thread(w1, "122448_Writer_1"),
                new Thread(w2, "122448_Writer_2"),
                new Thread(r, "122448_Reader_1"),
        };

        Thread stats = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                for (Thread t : workers) {
                    IO.print("-- " + t.getName() + " --");
                    IO.print(" { state: " + t.getState());
                    IO.print(" | is interrupted: " + t.isInterrupted() + " }");
                    IO.println();
                }
                IO.println("======================");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "122448_WorkerInfo_1");
        stats.setDaemon(true);

        for (Thread t : workers) {
            t.start();
        }

        stats.start();
        Thread.sleep(1_000);
        for (Thread t : workers) {
            t.interrupt();
        }
        Thread.sleep(50);

    }
}
