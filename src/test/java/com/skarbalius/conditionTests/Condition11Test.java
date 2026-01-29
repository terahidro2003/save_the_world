package com.skarbalius.conditionTests;

import com.skarbalius.LIC.Conditions;
import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Condition11Test
{
    @Test
    void testCondition11_XIncreases_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 0));  // i
        points.add(new Point(2, 1));  // intervening
        points.add(new Point(3, 2));  // j, X[j] - X[i] = 3 - 1 = 2 > 0

        Parameters_T params = new TestParameters();
        params.G_PTS = 1;

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC11(points, params));
    }

    @Test
    void testCondition11_InsufficientPoints_ReturnsFalse() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(5, 0));
        points.add(new Point(2, 1));

        Parameters_T params = new TestParameters();
        params.G_PTS = 1;

        Conditions conditions = new Conditions(points, params);
        assertFalse(conditions.LIC11(points, params));
    }

    @Test
    void testCondition11_XDecreases_ReturnsTrue() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(5, 0));  // i
        points.add(new Point(3, 1));  // intervening (G_PTS = 1)
        points.add(new Point(2, 2));  // j, X[j] - X[i] = 2 - 5 = -3 < 0

        Parameters_T params = new TestParameters();
        params.G_PTS = 1;

        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC11(points, params));
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

        Conditions conditions = new Conditions(points, params);
        assertTrue(conditions.LIC11(points, params));
    }
}
