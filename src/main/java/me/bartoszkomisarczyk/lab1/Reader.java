package me.bartoszkomisarczyk.lab1;

public class Reader implements Runnable {
    private Long sum;
    private final SharedResource resource;

    public Reader(SharedResource res) {
        this.sum = 0L;
        this.resource = res;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            //IO.println("Reader reading...");
            try {
                //IO.println(Thread.currentThread().getName() + " just got value!");
                this.sum += resource.getNumber();
            } catch (InterruptedException e) {
                IO.println("Reader interrupted -");
                Thread.currentThread().interrupt();
            } finally {
                IO.println("Final sum: " + this.sum);
            }
        }
    }

    public Long getSum() {
        return this.sum;
    }
}
