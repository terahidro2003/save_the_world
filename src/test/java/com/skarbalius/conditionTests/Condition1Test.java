package com.skarbalius.conditionTests;

import com.skarbalius.LIC.Conditions;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Condition1Test
{
    @Test
    void testCondition1_PointsOutsideRadius_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        // Equilateral triangle with side length ~17.3, circumradius = 10
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(5, 8.66f));

        Parameters_T params = new TestParameters();
        params.RADIUS1 = 5.0f; // smaller than required radius of ~5.77

        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC1(points, params));
    }

    @Test
    void testCondition1_PointsInsideRadius_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0.5f, 0.5f));

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, params);

        assertFalse(conditions.LIC1(points, params));
    }

    @Test
    void testCondition1_PointsExactlyOnRadius_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        // Three points on a circle of radius 5.0 at angles 0, 120, 240 degrees
        points.add(new Point(5.0f, 0.0f));
        points.add(new Point(-2.5f, 4.330127f));
        points.add(new Point(-2.5f, -4.330127f));

        Parameters_T params = new TestParameters();
        params.RADIUS1 = 5.0f; // exactly the circumradius of the three points

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC1(points, params));
    }

    @Test
    void testCondition1_TwoPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0.0f, 0.0f));
        points.add(new Point(1.0f, 1.0f)); // only two points, cannot form a triple

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, params);

        assertFalse(conditions.LIC1(points, params));
    }
}
