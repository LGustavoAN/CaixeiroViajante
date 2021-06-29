/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantec;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author 2018.1.08.025
 */
public class mFile {

    private static double[][] matrixCoordinates;
    private static double[][] matrixDistances;
    private static mFile lA = new mFile();
    
    private mFile() {
    }
    
 
    public static void readFile() {
        int i, j;
        try {
            File arquivo = new File("caixeiroviajantec/newInstance.txt");
            Scanner sc = new Scanner(arquivo);
            matrixCoordinates = new double[58][2];
            
            for (i = 0; i < matrixCoordinates.length; i++) {
                for (j = 0; j < matrixCoordinates[i].length; j++) {
                    matrixCoordinates[i][j] = sc.nextDouble();

                }

            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void calcDistances() {
        int i, j;
        double coordx, coordy;
        matrixDistances = new double[58][58];
        for (i = 0; i < matrixDistances.length; i++) {
            for (j = i + 1; j < matrixDistances.length; j++) {
                coordx = matrixCoordinates[j][0] - matrixCoordinates[i][0];
                coordy = matrixCoordinates[j][1] - matrixCoordinates[i][1];
                matrixDistances[i][j] = Math.sqrt(Math.pow(coordx, 2) + Math.pow(coordy, 2));
                matrixDistances[j][i] = matrixDistances[i][j];
            }
        }
    }

    public static mFile getlA() {
        return lA;
    }

    /**
     * @return the matrixCoordinates
     */
    public static double[][] getMatrixCoordinates() {
        if (matrixCoordinates == null) {
            readFile();
        }
        return matrixCoordinates;
    }

    /**
     * @return the matrixDistances
     */
    public static double[][] getMatrixDistances() {
        if (matrixCoordinates == null || matrixDistances == null) {
            readFile();
            calcDistances();
        }
        return matrixDistances;
    }
    public static void main(String[] args) {
        
    }

}