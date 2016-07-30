package com.alanrusnak.simulation;

/**
 * Created by alan on 30/07/16.
 */
public abstract class Philosopher implements Runnable{

    private final int id;
    private PhilosopherState state;

    private Table table;
    private Fork leftFork;
    private Fork rightFork;


    public Philosopher(int id, Table table, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.state = PhilosopherState.WAITING;
        this.table = table;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public final void run() {
        pickUpForks();
        eat();
        putDownForks();
        finishTurn();
    }

    protected abstract void pickUpForks();
    protected abstract void putDownForks();

    protected void pickUpLeftFork(){
        table.pickUpFork(this, leftFork);
    }

    protected void pickUpRightFork(){
        table.pickUpFork(this, rightFork);
    }

    protected void putDownLeftFork(){
        table.putDownFork(this, leftFork);
    }

    protected void putDownRightFork(){
        table.putDownFork(this, rightFork);
    }



    private final void eat(){
        state = PhilosopherState.EATING;
    }

    private final void finishTurn() {
        state = PhilosopherState.WAITING;
    }

    public PhilosopherState getState() {
        return state;
    }
}
