package com.skarbalius.conditionTests;

import com.skarbalius.LIC.Conditions;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Condition8Test {

    @Test
    void testCondition8_PointsCannotFitInCircle_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(5, 5));
        points.add(new Point(10, 0));
        points.add(new Point(15, 0));

        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 2.0;

        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC8(points, params));
    }

    @Test
    void testCondition8_PointsFitInCircle_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(1, 1));
        points.add(new Point(0, 1));
        points.add(new Point(0.5, 0.5));

        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 2.0;

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC8(points, params));
    }

    @Test
    void testCondition8_CollinearPointsOutsideRadius_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 0));
        points.add(new Point(4, 0));
        points.add(new Point(6, 0));
        points.add(new Point(8, 0));

        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 1.0;

        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC8(points, params));
    }

    @Test
    void testCondition8_CollinearPointsWithinRadius_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 3.0;

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC8(points, params));
    }

    @Test
    void testCondition8_SlidingWindowLaterSet_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(5, 5));
        points.add(new Point(10, 0));
        points.add(new Point(15, 0));

        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 2.0;

        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC8(points, params));
    }

    @Test
    void testCondition8_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));

        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 1.0;

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC8(points, params));
    }
}
