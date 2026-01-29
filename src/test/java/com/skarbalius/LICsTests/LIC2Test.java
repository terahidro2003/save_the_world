package com.skarbalius.LICsTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LIC2Test
{
    @Test
    void testCondition2_AngleOutsideRange_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 1)); // forms acute angle

        Parameters_T params = new TestParameters();
        LICs conditions = new LICs(points, params);

        assertTrue(conditions.LIC2(points, params));
    }

    @Test
    void testCondition2_AngleStraight_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 0)); // collinear, angle = PI

        Parameters_T params = new TestParameters();
        LICs conditions = new LICs(points, params);

        assertFalse(conditions.LIC2(points, params));
    }

    @Test
    void testCondition2_AngleEqualToPiMinusEpsilon_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        Parameters_T params = new TestParameters();

        double angle = Math.PI - params.EPSILON;
        points.add(new Point(1.0, 0.0));
        points.add(new Point(0.0, 0.0));
        points.add(new Point(Math.cos(angle), Math.sin(angle)));

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC2(points, params));
    }


    @Test
    void testCondition2_VertexCoincidesWithFirstPoint_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0.0f, 0.0f)); // first point
        points.add(new Point(0.0f, 0.0f)); // vertex coincides with first point (degenerate)
        points.add(new Point(1.0f, 1.0f)); // third point

        Parameters_T params = new TestParameters();
        LICs conditions = new LICs(points, params);

        assertFalse(conditions.LIC2(points, params));
    }
}
