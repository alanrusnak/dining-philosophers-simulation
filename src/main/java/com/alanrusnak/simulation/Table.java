package com.alanrusnak.simulation;

import com.alanrusnak.gui.swing.SwingGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alan on 30/07/16.
 */
public class Table {

    private static final Logger log = LoggerFactory.getLogger(Table.class);

    private long sleepTime;
    private boolean simulationRunning;
    private SwingGui swingGui;

    private List<Fork> forks;

    public Table(long sleepTime){
        this.sleepTime = sleepTime;
        initializeForks();
        simulationRunning = true;
    }

    public Table(int sleepTime, SwingGui swingGui) {
        this.sleepTime = sleepTime;
        this.swingGui = swingGui;
        initializeForks();
        simulationRunning = true;
    }

    private void initializeForks(){
        forks = new ArrayList<Fork>();
        for(int i = 0; i < 5; i++){
            forks.add(new Fork(i));
        }
    }

    public void pickUpFork(Philosopher philosopher, Fork fork) {
        synchronized(fork){
            log.info("{} wants to pick up {}", philosopher, fork);
            while(!fork.isAvailable()) {
                try {
                    log.info("{} waiting to pick up {}", philosopher, fork);
                    fork.wait();
                } catch (InterruptedException e) {
                    log.error("Error when {} was waiting for a fork", philosopher, e);
                }
            }
            fork.setCurrentOwner(philosopher);
            log.info("{} picked up {}", philosopher, fork);
            refreshGui();
        }

    }

    public void putDownFork(Philosopher philosopher, Fork fork) {
        synchronized(fork) {
            log.info("{} puts down {}", philosopher, fork);
            fork.setCurrentOwner(null);
            fork.notifyAll();
            refreshGui();
        }
    }

    private void refreshGui(){
        if(swingGui != null){
            swingGui.refresh();
        }
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public boolean isSimulationRunning() {
        return simulationRunning;
    }

    public void setSimulationRunning(boolean simulationRunning) {
        this.simulationRunning = simulationRunning;
    }

    public List<Fork> getForks() {
        return forks;
    }

}
