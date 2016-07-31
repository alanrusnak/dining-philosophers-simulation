package com.alanrusnak.gui.swing;

import javax.swing.*;

/**
 * Created by alan on 31/07/16.
 */
public class SwingGui extends JFrame {

    private PhilosophersPanel philosophersPanel;
    private ControlPanel controlPanel;

    public SwingGui(){
        initGui();
    }

    private void initGui() {
        setSize(700,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        philosophersPanel = new PhilosophersPanel();
        controlPanel = new ControlPanel(philosophersPanel);




        setVisible(true);
    }

}
