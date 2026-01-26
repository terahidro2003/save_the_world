package com.skarbalius.conditionTests;

import com.skarbalius.Conditions;
import com.skarbalius.Parameters_T;
import com.skarbalius.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Condition7Test
{
    @Test
    void testCondition7_DistanceGreaterThanLength1_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(5, 0));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;

        Conditions conditions = new Conditions(points, 3, params);
        assertTrue(conditions.condition7(points, 3, params, 3.0));
    }

    @Test
    void testCondition7_DistanceEqualToLength1_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition7(points, 3, params, 4.0));
    }

    @Test
    void testCondition7_DistanceLessThanLength1_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition7(points, 3, params, 5.0));
    }

    @Test
    void testCondition7_SlidingWindowFindsLaterPair_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(10, 10));

        Parameters_T params = new TestParameters();
        params.K_PTS = 2;

        Conditions conditions = new Conditions(points, 4, params);
        assertTrue(conditions.condition7(points, 4, params, 5.0));
    }

    @Test
    void testCondition7_MultiplePairsNoneValid_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;

        Conditions conditions = new Conditions(points, 4, params);
        assertFalse(conditions.condition7(points, 4, params, 5.0));
    }
}
