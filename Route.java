/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixeiroviajantec;

public class Route {

    private static int output[] = new int[58];

    public static int[] getOutput() {
        return output;
    }

    public static int getOutputPos(int index) {
        return output[index];
    }

    private Route() {
    }
}