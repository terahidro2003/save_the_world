package com.skarbalius.conditionTests;

import com.skarbalius.LIC.Conditions;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Condition13Test
{
    @Test
    void testCondition13_BothConditionsMet_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(10, 0),
                new Point(2, 2),
                new Point(0, 10)
        ));
        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 0.5;
        params.RADIUS2 = 10.0;
        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC13(points, params));
    }

    @Test
    void testCondition13_OnlyFirstConditionMet_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(10, 0),
                new Point(2, 2),
                new Point(0, 10)
        ));
        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 0.5;
        params.RADIUS2 = 0.1; // Too small

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC13(points, params));
    }

    @Test
    void testCondition13_LessThan5Points_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(10, 0),
                new Point(0, 10),
                new Point(5, 5)
        ));
        Parameters_T params = new TestParameters();
        params.A_PTS = 1;
        params.B_PTS = 1;
        params.RADIUS1 = 1.0;
        params.RADIUS2 = 10.0;

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC13(points, params));
    }
}
