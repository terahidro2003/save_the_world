package com.skarbalius;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ConditionsTest {

    @Test
    void testCondition0_PointsFarApart_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0)); // distance = 10
        points.add(new Point(15, 0));

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertTrue(conditions.condition0(points, 3, params));
    }

    @Test
    void testCondition0_PointsClose_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 0)); // distance = 2
        points.add(new Point(4, 0)); // distance = 2

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertFalse(conditions.condition0(points, 3, params));
    }

    @Test
    void testCondition1_PointsOutsideRadius_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        // Equilateral triangle with side length ~17.3, circumradius = 10
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(5, 8.66f));

        Parameters_T params = new TestParameters();
        params.RADIUS1 = 5.0f; // smaller than required radius of ~5.77

        Conditions conditions = new Conditions(points, 3, params);
        assertTrue(conditions.condition1(points, 3, params));
    }

    @Test
    void testCondition1_PointsInsideRadius_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0.5f, 0.5f));

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertFalse(conditions.condition1(points, 3, params));
    }

    @Test
    void testCondition2_AngleOutsideRange_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 1)); // forms acute angle

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertTrue(conditions.condition2(points, 3, params));
    }

    @Test
    void testCondition2_AngleStraight_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 0)); // collinear, angle = PI

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertFalse(conditions.condition2(points, 3, params));
    }
}