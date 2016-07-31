package com.alanrusnak.gui.swing;

import javax.swing.*;

/**
 * Created by alan on 31/07/16.
 */
public class ControlPanel extends JPanel {

    private PhilosophersPanel philosophersPanel;

    private JButton startSimulationButton;


    public ControlPanel(PhilosophersPanel philosophersPanel) {
        this.philosophersPanel = philosophersPanel;
        init();
    }

    private void init(){
        startSimulationButton = new JButton("Start Simulation");
        add(startSimulationButton);
    }
}
