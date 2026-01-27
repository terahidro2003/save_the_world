package com.skarbalius.conditionTests;

import com.skarbalius.Conditions;
import com.skarbalius.Parameters_T;
import com.skarbalius.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Condition10Test
{
    @Test
    void testCondition10_TriangleAreaExceedsThreshold_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));    // v1
        points.add(new Point(1, 1));    // intervening (E_PTS = 1)
        points.add(new Point(5, 0));    // v2
        points.add(new Point(2, 2));    // intervening (F_PTS = 1)
        points.add(new Point(5, 5));    // v3

        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 5.0f; // Triangle area is 12.5

        Conditions conditions = new Conditions(points, 5, params);
        assertTrue(conditions.condition10(points, params));
    }

    @Test
    void testCondition10_TriangleAreaBelowThreshold_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 0));
        points.add(new Point(2, 2));
        points.add(new Point(1, 1));

        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 10.0f;

        Conditions conditions = new Conditions(points, 5, params);
        assertFalse(conditions.condition10(points, params));
    }

    @Test
    void testCondition10_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));

        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 0.1f;

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition10(points, params));
    }
}
