package com.skarbalius.conditionTests;

import com.skarbalius.Conditions;
import com.skarbalius.Parameters_T;
import com.skarbalius.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Condition6Test {

    @Test
    void testCondition6_PointFarFromLine_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 5));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;

        Conditions conditions = new Conditions(points, 3, params);
        assertTrue(conditions.condition6(points, 3, params, 2));
    }

    @Test
    void testCondition6_AllPointsCloseToLine_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 1));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition6(points, 3, params, 2));
    }

    @Test
    void testCondition6_CoincidentEndpoints_PointFar_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(5, 0));
        points.add(new Point(0, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;

        Conditions conditions = new Conditions(points, 3, params);
        assertTrue(conditions.condition6(points, 3, params, 3));
    }

    @Test
    void testCondition6_CoincidentEndpoints_AllClose_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(0, 0));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition6(points, 3, params, 3));
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

        Conditions conditions = new Conditions(points, 5, params);
        assertTrue(conditions.condition6(points, 5, params, 2));
    }

    @Test
    void testCondition6_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));

        Parameters_T params = new TestParameters();
        params.N_PTS = 3;

        Conditions conditions = new Conditions(points, 2, params);
        assertFalse(conditions.condition6(points, 2, params, 1));
    }
}
