package com.skarbalius.utils;

import com.skarbalius.model.*;

public class MockDataGenerator {

    public static InputData createMockInput() {
        // NUMPOINTS
        int numPoints = 5;

        // POINTS
        Point[] points = new Point[numPoints];
        points[0] = new Point(0.0, 0.0);
        points[1] = new Point(1.0, 1.0);
        points[2] = new Point(2.0, 2.0);
        points[3] = new Point(3.0, 3.0);
        points[4] = new Point(4.0, 4.0);

        // PARAMETERS
        InputData.Parameters params = new InputData.Parameters();
        params.LENGTH1 = 1.0;
        params.RADIUS1 = 1.0;
        params.EPSILON = 0.1;
        params.AREA1 = 1.0;

        // LCM
        Enums[][] lcm = new Enums[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = Enums.NOTUSED; 
            }
        }
        lcm[0][1] = Enums.ANDD; 
        lcm[0][2] = Enums.ORR;  
        lcm[1][0] = Enums.ANDD; 
        lcm[2][0] = Enums.ORR;

        // PUV
        boolean[] puv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            puv[i] = false; 
        }
        puv[0] = true;
        puv[2] = true;
        puv[4] = true;
        puv[10] = true;

        return new InputData(numPoints, points, params, lcm, puv);
    }
}