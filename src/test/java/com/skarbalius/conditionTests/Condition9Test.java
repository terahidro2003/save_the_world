package com.skarbalius.conditionTests;

import com.skarbalius.Conditions;
import com.skarbalius.Parameters_T;
import com.skarbalius.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Condition9Test
{
    @Test
    void testCondition9_ValidAngleLessThanPIMinusEpsilon_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 0),  // vertex
                new Point(3, 3),
                new Point(3, 1)   // forms angle < PI - EPSILON
        ));
        Parameters_T params = new TestParameters();
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.EPSILON = 0.1;

        Conditions conditions = new Conditions(points, 5, params);
        assertTrue(conditions.condition9(points, params));
    }

    @Test
    void testCondition9_ValidAngleGreaterThanPIPlusEpsilon_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 0.1),  // vertex
                new Point(3, 3),
                new Point(3, -0.1)  // forms angle > PI + EPSILON
        ));
        Parameters_T params = new TestParameters();
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.EPSILON = 0.1;

        Conditions conditions = new Conditions(points, 5, params);
        assertTrue(conditions.condition9(points, params));
    }

    @Test
    void testCondition9_AngleWithinPIRange_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 0),  // vertex
                new Point(3, 3),
                new Point(4, 0)   // forms straight angle ~PI
        ));
        Parameters_T params = new TestParameters();
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.EPSILON = 0.5;

        Conditions conditions = new Conditions(points, 5, params);
        assertFalse(conditions.condition9(points, params));
    }

    @Test
    void testCondition9_VertexCoincidesWithFirstPoint_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(2, 0),
                new Point(1, 1),
                new Point(2, 0),  // vertex coincides with v1
                new Point(3, 3),
                new Point(4, 0)
        ));
        Parameters_T params = new TestParameters();
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.EPSILON = 0.1;

        Conditions conditions = new Conditions(points, 5, params);
        assertFalse(conditions.condition9(points, params));
    }

    @Test
    void testCondition9_LessThan5Points_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 0),
                new Point(3, 1)
        ));
        Parameters_T params = new TestParameters();
        params.C_PTS = 1;
        params.D_PTS = 1;
        params.EPSILON = 0.1;

        Conditions conditions = new Conditions(points, 4, params);
        assertFalse(conditions.condition9(points, params));
    }

    @Test
    void testCondition9_InvalidCPTS_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 0),
                new Point(3, 3),
                new Point(4, 0)
        ));
        Parameters_T params = new TestParameters();
        params.C_PTS = 0;  // Invalid: must be >= 1
        params.D_PTS = 1;
        params.EPSILON = 0.1;

        Conditions conditions = new Conditions(points, 5, params);
        assertFalse(conditions.condition9(points, params));
    }

}
