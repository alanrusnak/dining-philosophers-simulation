package com.alanrusnak.simulation;

/**
 * Created by alan on 30/07/16.
 */
public class OrderedPhilosopher extends Philosopher {

    public OrderedPhilosopher(int id, Table table, Fork leftFork, Fork rightFork) {
        super(id, table, leftFork, rightFork);
    }

    protected void pickUpForks() {
        if(getLeftFork().getId() < getRightFork().getId()){
            pickUpLeftFork();
            pickUpRightFork();
        } else {
            pickUpRightFork();
            pickUpLeftFork();
        }
    }

    protected void putDownForks() {
        putDownLeftFork();
        putDownRightFork();
    }
}
