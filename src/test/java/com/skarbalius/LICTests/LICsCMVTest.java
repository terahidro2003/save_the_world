package com.skarbalius.LICTests;

import com.skarbalius.LIC.LICs;
import com.skarbalius.LIC.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class LICsCMVTest {

    @Test
    void cmvHas15EntriesAndIsStable() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(2, 0));

        TestParameters p = new TestParameters();
        p.QUADS = 1;
        p.DIST = 1.0;

        LICs conditions = new LICs(points, p);

        Vector<Boolean> cmv1 = conditions.getCMV();
        assertEquals(15, cmv1.size(), "CMV must contain exactly 15 values");

        Vector<Boolean> cmv2 = conditions.getCMV();
        assertEquals(15, cmv2.size(), "CMV size must not change on repeated calls");
        assertEquals(cmv1, cmv2, "CMV contents must be stable across calls");
    }

    @Test
    void cmvEntriesMatchConditionMethods() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(10, 10));
        points.add(new Point(-5, 10));
        points.add(new Point(-5, -10));

        TestParameters p = new TestParameters();
        p.QUADS = 1;
        p.DIST = 2.0;

        LICs c = new LICs(points, p);
        Vector<Boolean> cmv = c.getCMV();

        assertEquals(15, cmv.size());

        assertEquals(c.LIC0(points, p),  cmv.get(0));
        assertEquals(c.LIC1(points, p),  cmv.get(1));
        assertEquals(c.LIC2(points, p),  cmv.get(2));
        assertEquals(c.LIC3(points, p),  cmv.get(3));
        assertEquals(c.LIC4(points, p),  cmv.get(4));
        assertEquals(c.LIC5(points, p),  cmv.get(5));
        assertEquals(c.LIC6(points, p),  cmv.get(6));
        assertEquals(c.LIC7(points, p),  cmv.get(7));
        assertEquals(c.LIC8(points, p),  cmv.get(8));
        assertEquals(c.LIC9(points, p),  cmv.get(9));
        assertEquals(c.LIC10(points, p), cmv.get(10));
        assertEquals(c.LIC11(points, p), cmv.get(11));
        assertEquals(c.LIC12(points, p), cmv.get(12));
        assertEquals(c.LIC13(points, p), cmv.get(13));
        assertEquals(c.LIC14(points, p), cmv.get(14));
    }

    @Test
    void smallInputProducesExpectedCmvPattern() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(10, 0));
        points.add(new Point(10, 10));

        TestParameters p = new TestParameters();
        p.QUADS = 1;
        p.DIST = 1.0;

        LICs c = new LICs(points, p);
        Vector<Boolean> cmv = c.getCMV();

        assertEquals(15, cmv.size());

        // Likely true with this geometry and default TestParameters
        assertTrue(cmv.get(0), "LIC0 should be true");
        assertTrue(cmv.get(2), "LIC2 should be true (non-straight angle)");
        assertTrue(cmv.get(3), "LIC3 should be true (triangle area)");
        assertTrue(cmv.get(7), "LIC7 should be true");

        // With only 3 points, all separated-triple conditions must be false
        for (int i = 8; i <= 14; i++) {
            assertFalse(cmv.get(i), "LIC" + i + " must be false for NUMPOINTS < 5");
        }
    }
}

