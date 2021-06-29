/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantec;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Plotting extends JPanel {

    private int[] output;
    private double[][] matrix;
    private Optimization s = new Optimization();
    private int Base[] = new int[58];

    public Plotting() {
        this.output = Route.getOutput();
        this.matrix = mFile.getMatrixCoordinates();
        for (int c = 0; c < 57; c++) {
            Base[c] = c;
        }
    }

    @Override

    public void paintComponent(Graphics graphs) {
        super.paintComponent(graphs);
        this.setBackground(Color.WHITE);

        graphs.setColor(Color.RED);
        for (int i = 0; i < 58; i++) {
            graphs.fillOval((int) (matrix[Base[i]][0] * 6 - 6), (int) (matrix[Base[i]][1] * 6 - 6), 11, 11);
        }

        for (int k = 0; k < 57; k++) {
            graphs.setColor(Color.RED);
            graphs.drawLine((int) (matrix[output[k]][0] * 6), (int) (matrix[output[k]][1] * 6),
                    (int) (matrix[output[k + 1]][0] * 6), (int) (matrix[output[k + 1]][1] * 6));
        }

        graphs.drawLine((int) (matrix[output[57]][0] * 6), (int) (matrix[output[57]][1] * 6),
                (int) (matrix[output[0]][0] * 6), (int) (matrix[output[0]][1] * 6));

        graphs.setColor(Color.BLACK);
        graphs.drawString("Score: " + s.sum(output), 50, 800);
    }

}