package com.alanrusnak.simulation;

/**
 * Created by alan on 30/07/16.
 */
public class DeadlockPhilosopher extends Philosopher {

    public DeadlockPhilosopher(int id, Table table, Fork leftFork, Fork rightFork) {
        super(id, table, leftFork, rightFork);
    }

    protected void pickUpForks() {
        pickUpLeftFork();
        pickUpRightFork();
    }

    protected void putDownForks() {
        putDownLeftFork();
        putDownRightFork();
    }


}
