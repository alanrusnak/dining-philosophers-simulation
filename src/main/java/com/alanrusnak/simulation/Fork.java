package com.alanrusnak.simulation;

/**
 * Created by alan on 30/07/16.
 */
public class Fork {

    private final int id;
    private volatile Philosopher currentOwner;

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

    @Override
    public String toString() {
        return "Fork " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fork fork = (Fork) o;

        return id == fork.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
