package com.skarbalius.conditionTests;

import com.skarbalius.LIC.Conditions;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Condition2Test
{
    @Test
    void testCondition2_AngleOutsideRange_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 1)); // forms acute angle

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, params);

        assertTrue(conditions.condition2(points, params));
    }

    @Test
    void testCondition2_AngleStraight_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 0)); // collinear, angle = PI

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, params);

        assertFalse(conditions.condition2(points, params));
    }
}
