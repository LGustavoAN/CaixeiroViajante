package caixeiroviajantec;

import javax.swing.*;

import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2018.1.08.025
 */
public class Window extends JPanel implements Observer, ActionListener {

    private double matrixCoordinates[][];
    private int output[];
    private JFrame window = new JFrame();
    private JButton button = new JButton();
    private Optimization ob = new Optimization();

    public Window() {
        matrixCoordinates = mFile.getMatrixCoordinates();
        output = Route.getOutput();
    }
    Plotting grafo = new Plotting();

    public void openWindow() {
        window.setExtendedState(MAXIMIZED_BOTH);
        window.setTitle("@");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button.setText("Play");
        button.setBounds(50, 900, 200, 50);
        button.addActionListener(this);
        Plotting grafo = new Plotting();
        ob.addObserver(this);
        window.add(button);
        window.add(grafo);
        window.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        window.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread(this.ob);
        thread.start();
        button.setEnabled(false);

    }
}