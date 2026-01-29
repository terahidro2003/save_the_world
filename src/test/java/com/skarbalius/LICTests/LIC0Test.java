package com.skarbalius.LICTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LIC0Test
{
    @Test
    void testCondition0_PointsFarApart_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0)); // distance = 10
        points.add(new Point(15, 0));

        Parameters_T params = new TestParameters();
        LICs conditions = new LICs(points, params);

        assertTrue(conditions.LIC0(points, params));
    }

    @Test
    void testCondition0_PointsClose_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 0)); // distance = 2
        points.add(new Point(4, 0)); // distance = 2

        Parameters_T params = new TestParameters();
        LICs conditions = new LICs(points, params);

        assertFalse(conditions.LIC0(points, params));
    }

    @Test
    void testCondition0_DistanceEqualToLength_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(5, 0)); // distance = 5 equals TestParameters.LENGTH1

        Parameters_T params = new TestParameters(); // LENGTH1 == 5.0f
        LICs conditions = new LICs(points, params);

        assertFalse(conditions.LIC0(points, params));
    }

    @Test
    void testCondition0_SinglePoint_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));

        Parameters_T params = new TestParameters();
        LICs conditions = new LICs(points, params);

        assertFalse(conditions.LIC0(points, params));
    }
}
