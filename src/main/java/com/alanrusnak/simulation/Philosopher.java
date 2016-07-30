package com.alanrusnak.simulation;

/**
 * Created by alan on 30/07/16.
 */
public abstract class Philosopher {

    private final int id;
    private PhilosopherState state;

    private Fork leftFork;
    private Fork rightFork;


    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        state = PhilosopherState.WAITING;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }
}
