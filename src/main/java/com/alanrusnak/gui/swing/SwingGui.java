package com.alanrusnak.gui.swing;

import com.alanrusnak.simulation.Simulation;
import com.alanrusnak.simulation.Table;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alan on 31/07/16.
 */
public class SwingGui extends JFrame {

    private PhilosophersPanel philosophersPanel;
    private ControlPanel controlPanel;
    private Simulation simulation;

    public SwingGui(){
        simulation = new Simulation();
        initGui();
    }

    private void initGui() {
        setSize(500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        philosophersPanel = new PhilosophersPanel(simulation);
        controlPanel = new ControlPanel(philosophersPanel);

        getContentPane().add(philosophersPanel, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout. SOUTH);

        setVisible(true);
    }

    public static void main(String[] args){
        SwingGui gui = new SwingGui();
    }
}