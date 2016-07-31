package com.alanrusnak.gui.swing;

import com.alanrusnak.simulation.Fork;
import com.alanrusnak.simulation.Philosopher;
import com.alanrusnak.simulation.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


/**
 * Created by alan on 31/07/16.
 */
public class PhilosophersPanel extends JPanel {

    private Logger log = LoggerFactory.getLogger(PhilosophersPanel.class);

    private BufferedImage backgroundImage;
    private BufferedImage forkImage;
    private BufferedImage fullPlateImage;

    private List<Point> forkPositions = Arrays.asList(new Point(300,100), new Point(200,100), new Point(120, 260), new Point(250,300), new Point(350,280));

    private Simulation simulation;

    public PhilosophersPanel(Simulation simulation) {
        this.simulation = simulation;
        loadImages();
    }

    private void loadImages() {
        backgroundImage = loadImage("philosophers.jpg");
        forkImage = loadImage("fork.jpg");
        fullPlateImage = loadImage("fullPlate.jpg");
    }

    private BufferedImage loadImage(String imageName){
        try {
            return ImageIO.read(new File("src/main/resources/" + imageName));
        } catch (IOException e) {
            log.error("Error reading image {}", imageName, e);
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackgroundImage(g);
        paintFullPlates(g);
        paintForks(g);
    }

    private void paintBackgroundImage(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }

    private void paintForks(Graphics g){
        for(Fork fork : simulation.getTable().getForks()){
            paintFork(g, fork);
        }
    }

    private void paintFork(Graphics g, Fork fork) {
        g.drawImage(forkImage, (int)forkPositions.get(fork.getId()).getX(), (int)forkPositions.get(fork.getId()).getY(), null);
    }

    private void paintFullPlates(Graphics g){
        for(Philosopher philosopher : simulation.getPhilosophers()){
            paintFullPlate(g, philosopher);
        }
    }

    private void paintFullPlate(Graphics g, Philosopher philosopher) {

    }

}