package com.skarbalius.LICsTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LIC7Test
{
    @Test
    void testCondition7_DistanceGreaterThanLength1_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(5, 0));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 3.0;

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC7(points, params));
    }

    @Test
    void testCondition7_DistanceEqualToLength1_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(4, 0));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 4.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC7(points, params));
    }

    @Test
    void testCondition7_DistanceLessThanLength1_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 5.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC7(points, params));
    }

    @Test
    void testCondition7_SlidingWindowFindsLaterPair_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(2, 2));
        points.add(new Point(10, 10));

        Parameters_T params = new TestParameters();
        params.K_PTS = 2;
        params.LENGTH1 = 5.0;

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC7(points, params));
    }

    @Test
    void testCondition7_MultiplePairsNoneValid_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));
        points.add(new Point(3, 0));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 5.0;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC7(points, params));
    }
}
