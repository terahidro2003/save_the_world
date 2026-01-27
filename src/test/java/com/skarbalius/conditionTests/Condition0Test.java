package com.skarbalius.conditionTests;

import com.skarbalius.Conditions;
import com.skarbalius.Parameters_T;
import com.skarbalius.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Condition0Test
{
    @Test
    void testCondition0_PointsFarApart_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0)); // distance = 10
        points.add(new Point(15, 0));

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertTrue(conditions.condition0(points, params));
    }

    @Test
    void testCondition0_PointsClose_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 0)); // distance = 2
        points.add(new Point(4, 0)); // distance = 2

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertFalse(conditions.condition0(points, params));
    }
}
