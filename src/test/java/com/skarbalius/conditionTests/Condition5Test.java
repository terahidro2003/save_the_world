package com.skarbalius.conditionTests;

import com.skarbalius.Conditions;
import com.skarbalius.Parameters_T;
import com.skarbalius.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Condition5Test
{
    @Test
    void testCondition5_XDecreases_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(5, 0));
        points.add(new Point(3, 1));   // x decreases (3 - 5 < 0)

        Parameters_T params = new TestParameters();

        Conditions conditions = new Conditions(points, 2, params);
        assertTrue(conditions.condition5(points, 2, params));
    }

    @Test
    void testCondition5_AllXIncreasing_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(2, 1));
        points.add(new Point(3, -1));
        points.add(new Point(4, 2));

        Parameters_T params = new TestParameters();

        Conditions conditions = new Conditions(points, 4, params);
        assertFalse(conditions.condition5(points, 4, params));
    }

    @Test
    void testCondition5_XEqual_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(2, 0));
        points.add(new Point(2, 5));   // x does not decrease

        Parameters_T params = new TestParameters();

        Conditions conditions = new Conditions(points, 2, params);
        assertFalse(conditions.condition5(points, 2, params));
    }

    @Test
    void testCondition5_DecreaseLaterInSequence_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(5, 0));
        points.add(new Point(3, 0));   // decrease here

        Parameters_T params = new TestParameters();

        Conditions conditions = new Conditions(points, 4, params);
        assertTrue(conditions.condition5(points, 4, params));
    }

    @Test
    void testCondition5_OnlyOnePoint_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 1));

        Parameters_T params = new TestParameters();

        Conditions conditions = new Conditions(points, 1, params);
        assertFalse(conditions.condition5(points, 1, params));
    }
}
