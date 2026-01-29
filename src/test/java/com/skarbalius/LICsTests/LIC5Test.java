package com.skarbalius.LICsTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LIC5Test
{
    @Test
    void testCondition5_XDecreases_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(5, 0));
        points.add(new Point(3, 1));   // x decreases (3 - 5 < 0)

        Parameters_T params = new TestParameters();

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC5(points, params));
    }

    @Test
    void testCondition5_AllXIncreasing_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(2, 1));
        points.add(new Point(3, -1));
        points.add(new Point(4, 2));

        Parameters_T params = new TestParameters();

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC5(points, params));
    }

    @Test
    void testCondition5_XEqual_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(2, 0));
        points.add(new Point(2, 5));   // x does not decrease

        Parameters_T params = new TestParameters();

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC5(points, params));
    }

    @Test
    void testCondition5_DecreaseLaterInSequence_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(5, 0));
        points.add(new Point(3, 0));   // decrease here

        Parameters_T params = new TestParameters();

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC5(points, params));
    }

    @Test
    void testCondition5_OnlyOnePoint_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));

        Parameters_T params = new TestParameters();

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC5(points, params));
    }
}
