package com.skarbalius;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ConditionsTest
{

    @Test
    void testCondition0_PointsFarApart_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0)); // distance = 10
        points.add(new Point(15, 0));

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertTrue(conditions.condition0(points, 3, params));
    }

    @Test
    void testCondition0_PointsClose_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(2, 0)); // distance = 2
        points.add(new Point(4, 0)); // distance = 2

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertFalse(conditions.condition0(points, 3, params));
    }

    @Test
    void testCondition1_PointsOutsideRadius_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        // Equilateral triangle with side length ~17.3, circumradius = 10
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(5, 8.66f));

        Parameters_T params = new TestParameters();
        params.RADIUS1 = 5.0f; // smaller than required radius of ~5.77

        Conditions conditions = new Conditions(points, 3, params);
        assertTrue(conditions.condition1(points, 3, params));
    }

    @Test
    void testCondition1_PointsInsideRadius_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0.5f, 0.5f));

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertFalse(conditions.condition1(points, 3, params));
    }

    @Test
    void testCondition2_AngleOutsideRange_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 1)); // forms acute angle

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertTrue(conditions.condition2(points, 3, params));
    }

    @Test
    void testCondition2_AngleStraight_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0)); // vertex
        points.add(new Point(2, 0)); // collinear, angle = PI

        Parameters_T params = new TestParameters();
        Conditions conditions = new Conditions(points, 3, params);

        assertFalse(conditions.condition2(points, 3, params));
    }

    @Test
    void testCondition10_TriangleAreaExceedsThreshold_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));    // v1
        points.add(new Point(1, 1));    // intervening (E_PTS = 1)
        points.add(new Point(5, 0));    // v2
        points.add(new Point(2, 2));    // intervening (F_PTS = 1)
        points.add(new Point(5, 5));    // v3

        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 5.0f; // Triangle area is 12.5

        Conditions conditions = new Conditions(points, 5, params);
        assertTrue(conditions.condition10(points, 5, params));
    }

    @Test
    void testCondition10_TriangleAreaBelowThreshold_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(1, 0));
        points.add(new Point(2, 2));
        points.add(new Point(1, 1));

        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 10.0f;

        Conditions conditions = new Conditions(points, 5, params);
        assertFalse(conditions.condition10(points, 5, params));
    }

    @Test
    void testCondition10_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));

        Parameters_T params = new TestParameters();
        params.E_PTS = 1;
        params.F_PTS = 1;
        params.AREA1 = 0.1f;

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition10(points, 3, params));
    }

    @Test
    void testCondition11_XDecreases_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(5, 0));  // i
        points.add(new Point(3, 1));  // intervening (G_PTS = 1)
        points.add(new Point(2, 2));  // j, X[j] - X[i] = 2 - 5 = -3 < 0

        Parameters_T params = new TestParameters();
        params.G_PTS = 1;

        Conditions conditions = new Conditions(points, 3, params);
        assertTrue(conditions.condition11(points, 3, params));
    }

    @Test
    void testCondition11_XIncreases_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));  // i
        points.add(new Point(2, 1));  // intervening
        points.add(new Point(3, 2));  // j, X[j] - X[i] = 3 - 1 = 2 > 0

        Parameters_T params = new TestParameters();
        params.G_PTS = 1;

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition11(points, 3, params));
    }

    @Test
    void testCondition11_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(5, 0));
        points.add(new Point(2, 1));

        Parameters_T params = new TestParameters();
        params.G_PTS = 1;

        Conditions conditions = new Conditions(points, 2, params);
        assertFalse(conditions.condition11(points, 2, params));
    }

    @Test
    void testCondition11_MultipleInterveningPoints_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(10, 0));  // i
        points.add(new Point(8, 1));   // intervening
        points.add(new Point(7, 2));   // intervening
        points.add(new Point(5, 3));   // j, X[j] - X[i] = 5 - 10 = -5 < 0

        Parameters_T params = new TestParameters();
        params.G_PTS = 2;

        Conditions conditions = new Conditions(points, 4, params);
        assertTrue(conditions.condition11(points, 4, params));
    }

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

        Conditions conditions = new Conditions(points, 5, params);
        assertTrue(conditions.condition12(points, 5, params));
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

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition12(points, 3, params));
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

        Conditions conditions = new Conditions(points, 3, params);
        assertFalse(conditions.condition12(points, 3, params));
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

        Conditions conditions = new Conditions(points, 2, params);
        assertFalse(conditions.condition12(points, 2, params));
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

        Conditions conditions = new Conditions(points, 3, params);
        assertTrue(conditions.condition12(points, 3, params));
    }

}