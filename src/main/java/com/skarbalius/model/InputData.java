/**
 * TODO data need to be provided, now it
 */

package com.skarbalius.model;

import com.skarbalius.model.Enums;
import com.skarbalius.model.Point;

public class InputData {
    private int numPoints; // NUMPOINTS 
    private Point[] points; // POINTS array 
    private Parameters parameters; // PARAMETERS struct 
    private Enums[][] lcm; // Logical Connector Matrix 
    private boolean[] puv; // Preliminary Unlocking Vector 

    public InputData(int numPoints, Point[] points, Parameters parameters, Enums[][] lcm, boolean[] puv) {
        this.numPoints = numPoints;
        this.points = points;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
    }


    public Enums[][] getLcm() { return lcm; }
    public boolean[] getPuv() { return puv; }
    public Point[] getPoints() { return points; }
    public int getNumPoints() { return numPoints; }
    
    // PARAMETERS
    public static class Parameters {
        public double LENGTH1; 
        public double RADIUS1; 
        public double EPSILON; 
        public double AREA1;   
        // ... 
    }
}