package me.bartoszkomisarczyk.lab1;

public class SharedResource {
    private Integer number = null;

    public synchronized void setNumber(Integer val) throws InterruptedException {
        while (this.number != null) wait();

        this.number = val;
        notifyAll();
    }

    public synchronized Integer getNumber() throws InterruptedException {
        while (this.number == null) wait();

        Integer temp = this.number;
        this.number = null;
        notifyAll();
        return temp;
    }
}
