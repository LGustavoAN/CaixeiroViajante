/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantec;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Optimization extends Observable implements Runnable{

    private int output[] = new int[58];
    private double matrixDistances[][];
    private int known[] = new int[58];

    public Optimization() {
        this.matrixDistances = mFile.getMatrixDistances();
        this.output = Route.getOutput();
    }

    public double sum(int[] output) {
        int i;
        double totalS = 0;
        for (i = 0; i < output.length - 1; i++) {
            totalS = totalS + matrixDistances[output[i]][output[i + 1]];
        }
        totalS = totalS + matrixDistances[output[57]][output[0]];
        return totalS;
    }

    public int[] reverse(int[] output, int i, int j) {
        int s = j, aux;
        int nOutput[] = new int[58];
        for (int a = 0; a <= i - 1; a++) {
            nOutput[a] = output[a];
        }
        int d = 0;
        for (int t = i; t <= j; t++) {
            nOutput[t] = output[j - d];
            d++;
        }
        for (int h = j + 1; h < output.length; h++) {
            nOutput[h] = output[h];
        }
        return nOutput;
    }

    public void _2opt() throws InterruptedException  {
        
        double smSum = sum(output);
        double s1 = 0;
        int t = 0;
        int nOutput[] = new int[58];
        while (t < 1000) {
            for (int i = 0; i < output.length - 1; i++) {
                for (int j = i + 1; j < output.length; j++) {
                    nOutput = reverse(output, i, j);
                    s1 = sum(nOutput);
                    if (smSum > s1) {
                        for (int c = 0; c < output.length; c++) {
                            output[c] = nOutput[c];
                        }
                        smSum = s1;
                        change();
                    }
                }
            }
            t++;
        }
    }

    public int smSearch(int i) {
        int j;
        double sm = 10000000;
        int smIndex = 0;
        for (j = 0; j < matrixDistances.length; j++) {
            if (matrixDistances[i][j] < sm && known[j] != 1) {
                sm = matrixDistances[i][j];
                smIndex = j;
            }

        }
        known[smIndex] = 1;
        return smIndex;
    }

    public void nearestNeighbor(int inicial) throws InterruptedException {
        int i;
        output[0] = inicial;
        known[inicial] = 1;
        for (i = 1; i < matrixDistances.length; i++) {
            output[i] = smSearch(output[i - 1]);
            change();
        }
    }

    public void hillClimbing() throws InterruptedException {
        int i = 0, n1, n2;
        Random rd = new Random();
        double s1, s2;
        int aux, j;
        while (i < 1000) {
            n1 = rd.nextInt(58);
            for (j = 0; j < output.length; j++) {
                s1 = sum(output);
                aux = output[n1];
                output[n1] = output[j];
                output[j] = aux;
                s2 = sum(output);
                if (s1 < s2) {
                    s1 = sum(output);
                    aux = output[n1];
                    output[n1] = output[j];
                    output[j] = aux;
                }
                i++;

            }
        }
        change();

    }

    public void change() throws InterruptedException {
        setChanged();
        notifyObservers();
        Thread.sleep(100);
    }

    @Override
    public void run() {
        try {
            this.nearestNeighbor(13);
            this.hillClimbing();
            this._2opt();
        } catch (InterruptedException ex) {
            Logger.getLogger(Optimization.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

}