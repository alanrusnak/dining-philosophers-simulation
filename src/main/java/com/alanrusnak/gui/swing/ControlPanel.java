package com.alanrusnak.gui.swing;

import com.alanrusnak.simulation.Simulation;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
        addStopSimulationButton();
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

    private void addStopSimulationButton(){
        final JButton stopSimulationButton = new JButton("Stop Simulation");
        stopSimulationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                simulation.stopSimulation();
            }
        });
        add(stopSimulationButton);
    }

    private void addSimulationSpeedSelect(){
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 250, 1000, 250);
        slider.setMajorTickSpacing(250);
        slider.setSnapToTicks(true);
        slider.setPaintTicks(false);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JSlider source = (JSlider)changeEvent.getSource();
                if (!source.getValueIsAdjusting()) {
                    int sleepTime = (int)source.getValue();
                    simulation.setSleepTime(sleepTime);
                }
            }
        });

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
