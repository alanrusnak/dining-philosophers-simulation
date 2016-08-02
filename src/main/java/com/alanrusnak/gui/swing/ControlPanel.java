package com.alanrusnak.gui.swing;

import com.alanrusnak.simulation.Simulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alan on 31/07/16.
 */
public class ControlPanel extends JPanel {

    private Simulation simulation;


    public ControlPanel(Simulation simulation) {
        this.simulation = simulation;
        init();
    }

    private void init(){
        JButton startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                simulation.startSimulation();
            }
        });
        add(startSimulationButton);
    }
}
