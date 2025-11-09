package me.bartoszkomisarczyk.lab1;

public class Consumer implements Runnable {
    private Long sum;
    private final SharedResource resource;

    public Consumer(SharedResource res) {
        this.sum = 0L;
        this.resource = res;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                this.sum += resource.getNumber();
            } catch (InterruptedException e) {
                IO.println("Reader interrupted -");
                Thread.currentThread().interrupt();
            }
        }
        IO.println("Final sum: " + this.sum);
    }
}
