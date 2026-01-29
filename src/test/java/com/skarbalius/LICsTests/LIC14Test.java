package com.skarbalius.LICsTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LIC14Test
{
    @Test
    void testCondition14_BothConditionsMet_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 5),
                new Point(10, 5),  // Large triangle
                new Point(2, 2),
                new Point(10, 4.5)  // Small triangle
        ));
        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 1.0;
        params.AREA2 = 10.0;

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC14(points, params));
    }

    @Test
    void testCondition14_OnlyFirstConditionMet_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(10, 10),
                new Point(2, 2),
                new Point(5, 5)
        ));
        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 1.0;
        params.AREA2 = 0.1;  // Too small, no triangle can be less than this


        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC14(points, params));
    }

    @Test
    void testCondition14_OnlySecondConditionMet_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(0.1, 0),
                new Point(0, 0.1),
                new Point(0.2, 0),
                new Point(0, 0.2)
        ));
        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 100.0;  // Too large, no triangle can be greater than this
        params.AREA2 = 10.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC14(points, params));
    }

    @Test
    void testCondition14_LessThan5Points_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(10, 0),
                new Point(0, 10),
                new Point(5, 5)
        ));
        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 1.0;
        params.AREA2 = 100.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC14(points, params));
    }

    @Test
    void testCondition14_NegativeAREA2_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(10, 10),
                new Point(2, 2),
                new Point(0, 10)
        ));
        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 1.0;
        params.AREA2 = -1.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC14(points, params));
    }

}
