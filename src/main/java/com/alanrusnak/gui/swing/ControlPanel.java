package com.alanrusnak.gui.swing;

import com.alanrusnak.simulation.Simulation;

import javax.swing.*;
import java.awt.*;
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
        setPreferredSize(new Dimension(500,100));
        addStartNewSimulationButton();
        addPauseOrContinueButton();
        addSimulationSpeedSelect();
        addPhilosoperTypeRadio();
    }

    private void addStartNewSimulationButton(){
        final JButton startSimulationButton = new JButton("Start New Simulation");
        startSimulationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                simulation.startSimulation();
            }
        });
        add(startSimulationButton);
    }

    private void addPauseOrContinueButton(){
        final JButton pauseOrContinueButton = new JButton("Pause Simulation");
        pauseOrContinueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                simulation.togglePause();
                pauseOrContinueButton.setText(simulation.isPaused() ? "Continue Simulation" : "Pause Simulation");
            }
        });
        add(pauseOrContinueButton);
    }

    private void addSimulationSpeedSelect(){
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 250, 1000, 250);
        slider.setMajorTickSpacing(250);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(false);
        slider.setPaintLabels(true);

        add(slider);
    }

    private void addPhilosoperTypeRadio(){
        JRadioButton orderedButton = new JRadioButton("Ordered");
        JRadioButton deadlockButton = new JRadioButton("Deadlock");
        orderedButton.setSelected(true);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(orderedButton);
        buttonGroup.add(deadlockButton);
        add(orderedButton);
        add(deadlockButton);
    }
}
