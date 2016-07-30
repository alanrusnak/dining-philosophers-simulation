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

    public static void main(String[] args){
        log.info("Running main in Simulation");
        Table table = new Table(1);
        List<Philosopher> philosophers = createPhilosophers(table);

        ExecutorService executor = Executors.newCachedThreadPool();
        for (Philosopher philosopher : philosophers){
            log.info("Start {}", philosopher);
            executor.execute(philosopher);
        }

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
}
