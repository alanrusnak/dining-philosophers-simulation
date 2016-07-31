package com.alanrusnak.simulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alan on 30/07/16.
 */
public class Simulation {

    private static final Logger log = LoggerFactory.getLogger(Simulation.class);

    private Table table;
    private List<Philosopher> philosophers;

    public Simulation(){
        super();
        table = new Table(10);
        philosophers = createPhilosophers(table);
    }

    public void startSimulation(){
        log.info("Starting simulation");
        table = new Table(10);
        philosophers = createPhilosophers(table);

        ExecutorService executor = Executors.newCachedThreadPool();
        for (Philosopher philosopher : philosophers){
            log.info("Start {}", philosopher);
            executor.execute(philosopher);
        }
    }

    public static void main(String[] args){
        new Simulation().startSimulation();

    }

    public static List<Philosopher> createPhilosophers(Table table){
        List<Fork> forks = table.getForks();

        List<Philosopher> philosophers = new ArrayList<Philosopher>();
        for(int i = 0; i < 5; i++){
            Fork leftFork = forks.get(i);
            Fork rightFork = forks.get((i+1)%5);
            philosophers.add(new DeadlockPhilosopher(i, table, leftFork, rightFork));
        }
        return philosophers;
    }

    public Table getTable(){
        return table;
    }

    public List<Philosopher> getPhilosophers() {
        return philosophers;
    }
}
