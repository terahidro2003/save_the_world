package com.skarbalius.LICTests;

import com.skarbalius.LIC.Parameters_T;

public class TestParameters extends Parameters_T
{
    public TestParameters() {
        // Distance and radius thresholds
        LENGTH1 = 5.0f;
        LENGTH2 = 10.0f;
        RADIUS1 = 3.0f;
        RADIUS2 = 8.0f;

        // Area thresholds
        AREA1 = 20.0f;
        AREA2 = 50.0f;

        // Angle tolerance (in radians, ~5.7 degrees)
        EPSILON = 0.1f;

        // Gap parameters (number of intervening points)
        Q_PTS = 2;
        N_PTS = 3;
        K_PTS = 1;
        A_PTS = 1;
        B_PTS = 1;
        C_PTS = 1;
        D_PTS = 1;
        E_PTS = 1;
        F_PTS = 1;
        G_PTS = 1;
    }
}