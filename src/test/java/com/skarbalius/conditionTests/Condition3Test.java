package com.skarbalius.conditionTests;

import com.skarbalius.LIC.Conditions;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class Condition3Test
{
    @Test
    void testCondition3_AreaLargerThanParameter_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(0, 10));

        Parameters_T params = new TestParameters();
        params.AREA1 = 40.0;

        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC3(points, params));
    }

    @Test
    void testCondition3_AreaSmallerThanParameter_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));

        Parameters_T params = new TestParameters();
        params.AREA1 = 5.0;

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC3(points, params));
    }

    @Test
    void testCondition3_CollinearPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(5, 0));
        points.add(new Point(10, 0));

        Parameters_T params = new TestParameters();
        params.AREA1 = 0.1; 

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC3(points, params));
    }
}