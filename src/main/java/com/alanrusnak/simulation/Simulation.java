package com.alanrusnak.simulation;

import com.alanrusnak.gui.swing.SwingGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.alanrusnak.simulation.SimulationType.DEADLOCK;

/**
 * Created by alan on 30/07/16.
 */
public class Simulation {

    private static final Logger log = LoggerFactory.getLogger(Simulation.class);

    private Table table;
    private List<Philosopher> philosophers;
    private SwingGui swingGui;

    //private volatile boolean isRunning;
    private volatile int DEFAULT_SLEEP_TIME = 250;

    public Simulation(){
        super();
        table = new Table(DEFAULT_SLEEP_TIME);
        philosophers = createPhilosophers(table, SimulationType.DEADLOCK);
    }

    public Simulation(SwingGui swingGui, SimulationType simulationType){
        super();
        this.swingGui = swingGui;
        table = new Table(DEFAULT_SLEEP_TIME, swingGui);
        philosophers = createPhilosophers(table, simulationType);
    }

    public void startSimulation(SimulationType simulationType, int sleepTime){
        log.info("Starting simulation");
        table = new Table(sleepTime, swingGui);
        philosophers = createPhilosophers(table, simulationType);

        ExecutorService executor = Executors.newCachedThreadPool();
        for (Philosopher philosopher : philosophers){
            log.info("Start {}", philosopher);
            executor.execute(philosopher);
        }
    }

    public static void main(String[] args){
        new Simulation().startSimulation(SimulationType.ORDERED, 250);

    }

    public static List<Philosopher> createPhilosophers(Table table, SimulationType simulationType){


        List<Philosopher> philosophers = new ArrayList<Philosopher>();
        for(int i = 0; i < 5; i++){
            Philosopher philosopher = getNewPhilosoperForSimulationType(table, i, simulationType);
            philosophers.add(philosopher);
        }
        return philosophers;
    }

    private static Philosopher getNewPhilosoperForSimulationType(Table table, int i, SimulationType simulationType) {
        List<Fork> forks = table.getForks();
        Fork leftFork = forks.get(i);
        Fork rightFork = forks.get((i+1)%5);

        switch (simulationType){
            case DEADLOCK: return new DeadlockPhilosopher(i, table, leftFork, rightFork);
            case ORDERED: return new OrderedPhilosopher(i, table, leftFork, rightFork);
            default:
                log.error("Invalid Simulation Type {}", simulationType);
                return null;
        }
    }

    public Table getTable(){
        return table;
    }

    public List<Philosopher> getPhilosophers() {
        return philosophers;
    }

    public void stopSimulation(){ table.setSimulationRunning(false);}

    public void setSleepTime(int sleepTime) {
        table.setSleepTime(sleepTime);
    }
}
