package com.skarbalius.LICsTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LIC6Test {

    @Test
    void testCondition6_PointFarFromLine_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 5));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;
        params.DIST = 2.0;

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC6(points, params));
    }

    @Test
    void testCondition6_AllPointsCloseToLine_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 1));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;
        params.DIST = 2.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC6(points, params));
    }

    @Test
    void testCondition6_CoincidentEndpoints_PointFar_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(5, 0));
        points.add(new Point(0, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;
        params.DIST = 3.0;

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC6(points, params));
    }

    @Test
    void testCondition6_CoincidentEndpoints_AllClose_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(0, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;
        params.DIST = 3.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC6(points, params));
    }

    @Test
    void testCondition6_SlidingWindowLaterBlock_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(2, 4));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;
        params.DIST = 2.0;

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC6(points, params));
    }

    @Test
    void testCondition6_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;
        params.DIST = 1.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC6(points, params));
    }
}
