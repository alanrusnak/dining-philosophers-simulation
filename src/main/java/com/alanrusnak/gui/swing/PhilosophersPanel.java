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

    private List<Point> forkPositions = Arrays.asList(new Point(300,230), new Point(210,220), new Point(170, 290), new Point(240,340), new Point(320,320));
    private List<Integer> forkRotations = Arrays.asList(220,150,60,0,280);
    private List<Point> platePositions = Arrays.asList(new Point(212,130), new Point(105,205), new Point(143,336), new Point(277,338), new Point(318,211));

    private Simulation simulation;

    public PhilosophersPanel(Simulation simulation) {
        this.simulation = simulation;
        loadImages();
    }

    private void loadImages() {
        backgroundImage = loadImage("philosophers.jpg");
        forkImage = loadImage("fork.png");
        fullPlateImage = loadImage("fullPlate.png");
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
        final Graphics2D g2d = (Graphics2D)g.create();
        try {
            g2d.translate((int)forkPositions.get(fork.getId()).getX(),(int)forkPositions.get(fork.getId()).getY());
            g2d.rotate(Math.toRadians(forkRotations.get(fork.getId())));
            g2d.drawImage(forkImage, 0, 0, null);
        } finally {
            g2d.dispose();
        }
    }

    private void paintFullPlates(Graphics g){
        for(Philosopher philosopher : simulation.getPhilosophers()){
            paintFullPlate(g, philosopher);
        }
    }

    private void paintFullPlate(Graphics g, Philosopher philosopher) {
        final Graphics2D g2d = (Graphics2D)g.create();
        try {
            g2d.translate((int)platePositions.get(philosopher.getId()).getX(),(int)platePositions.get(philosopher.getId()).getY());
            g2d.drawImage(fullPlateImage, 0, 0, null);
        } finally {
            g2d.dispose();
        }
    }

}
