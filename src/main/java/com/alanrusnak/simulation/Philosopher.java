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
        while(table.isSimulationRunning()){
            pickUpForks();
            eat();
            sleep();
            putDownForks();
            finishTurn();
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(table.getSleepTime() + (int) (Math.random () * 6 - 3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract void pickUpForks();
    protected abstract void putDownForks();

    protected final void pickUpLeftFork(){
        table.pickUpFork(this, leftFork);
    }

    protected final void pickUpRightFork(){
        table.pickUpFork(this, rightFork);
    }

    protected final void putDownLeftFork(){
        table.putDownFork(this, leftFork);
    }

    protected final void putDownRightFork(){
        table.putDownFork(this, rightFork);
    }

    public final Fork getLeftFork() {
        return leftFork;
    }

    public final Fork getRightFork() {
        return rightFork;
    }

    private final void eat(){
        state = PhilosopherState.EATING;
    }

    private final void finishTurn() {
        state = PhilosopherState.WAITING;
    }

    public boolean hasBothForks() {
        return this.equals(leftFork.getCurrentOwner()) && this.equals(rightFork.getCurrentOwner());
    }

    public PhilosopherState getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
