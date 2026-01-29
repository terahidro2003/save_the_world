package com.skarbalius.LICsTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LIC12Test
{
    @Test
    void testCondition12_BothConditionsMet_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));    // pair 1: distance = 10
        points.add(new Point(1, 1));    // intervening (K_PTS = 1)
        points.add(new Point(10, 0));
        points.add(new Point(2, 2));    // intervening
        points.add(new Point(5.3, 2));  // pair 2: distance = 0.5

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 5.0f;   // 10 > 5
        params.LENGTH2 = 2.5f;   // 0.5 < 1

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC12(points, params));
    }

    @Test
    void testCondition12_OnlyGreaterConditionMet_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(10, 0));   // distance = 10

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 5.0f;   // 10 > 5 ✓
        params.LENGTH2 = 0.5f;   // 10 < 0.5 ✗

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC12(points, params));
    }

    @Test
    void testCondition12_OnlyLessConditionMet_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(0.5, 0));  // distance = 0.5

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 5.0f;   // 0.5 > 5 ✗
        params.LENGTH2 = 1.0f;   // 0.5 < 1 ✓

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC12(points, params));
    }

    @Test
    void testCondition12_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 5.0f;
        params.LENGTH2 = 15.0f;

        LICs conditions = new LICs(points, params);
        assertFalse(conditions.LIC12(points, params));
    }

    @Test
    void testCondition12_SamePairSatisfiesBoth_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(3, 0));    // distance = 3

        Parameters_T params = new TestParameters();
        params.K_PTS = 1;
        params.LENGTH1 = 2.0f;   // 3 > 2 ✓
        params.LENGTH2 = 5.0f;   // 3 < 5 ✓

        LICs conditions = new LICs(points, params);
        assertTrue(conditions.LIC12(points, params));
    }
}
