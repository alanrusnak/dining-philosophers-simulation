package com.alanrusnak.simulation;

/**
 * Created by alan on 30/07/16.
 */
public class Fork {

    private final int id;
    private Philosopher currentOwner;

    public Fork(int id){
        this.id = id;
        currentOwner = null;
    }

    public boolean isAvailable() {
        return currentOwner == null;
    }

    public int getId() {
        return id;
    }

    public Philosopher getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Philosopher currentOwner) {
        this.currentOwner = currentOwner;
    }
}
