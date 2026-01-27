package com.skarbalius;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PointWindows
{
    static boolean anyConsecutiveTriple(List<Point> pts, TriPredicate<Point, Point, Point> matchPred,
                                        TriPredicate<Point, Point, Point> skipPred) {
        for (int i = 2; i < pts.size(); i++) {
            Point v1 = pts.get(i - 2);
            Point v2 = pts.get(i - 1);
            Point v3 = pts.get(i);
            if (skipPred.test(v1, v2, v3)) {continue;}
            if (matchPred.test(v1, v2, v3)) {return true;}
        }
        return false;
    }

    static boolean anyConsecutiveTriple(List<Point> pts, TriPredicate<Point, Point, Point> pred) {
        return anyConsecutiveTriple(pts, pred, (a, b, c) -> false);
    }

    static boolean anyConsecutivePair(List<Point> pts, BiPredicate<Point, Point> pred) {
        for (int i = 1; i < pts.size(); i++) {
            if (pred.test(pts.get(i - 1), pts.get(i))) {return true;}
        }
        return false;
    }

    static boolean anySeparatedTriple(List<Point> pts, int gap1, int gap2, TriPredicate<Point, Point, Point> matchPred,
                                      TriPredicate<Point, Point, Point> skipPred) {
        int n = pts.size();
        if (n < 5 || gap1 < 1 || gap2 < 1 || gap1 + gap2 > n - 3) {return false;}

        for (int i = gap1 + gap2 + 2; i < n; i++) {
            Point v1 = pts.get(i - gap2 - gap1 - 2);
            Point v2 = pts.get(i - gap2 - 1);
            Point v3 = pts.get(i);
            if (skipPred.test(v1, v2, v3)) {continue;}
            if (matchPred.test(v1, v2, v3)) {return true;}
        }
        return false;
    }

    static boolean anySeparatedTriple(List<Point> pts, int gap1, int gap2, TriPredicate<Point, Point, Point> pred) {
        return anySeparatedTriple(pts, gap1, gap2, pred, (a, b, c) -> false);
    }

    static List<Triple> separatedTriples(List<Point> pts, int aPts, int bPts) {
        int n = pts.size();
        if (n < 5 || aPts < 1 || bPts < 1 || aPts + bPts > n - 3) {return List.of();}

        List<Triple> out = new ArrayList<>();
        for (int i = aPts + bPts + 2; i < n; i++) {
            out.add(new Triple(pts.get(i - bPts - aPts - 2), pts.get(i - bPts - 1), pts.get(i)));
        }
        return out;
    }

    static <T> boolean existsBoth(Iterable<T> items, Predicate<T> pred1, Predicate<T> pred2) {
        boolean f1 = false, f2 = false;
        for (T t : items) {
            if (!f1 && pred1.test(t)) {f1 = true;}
            if (!f2 && pred2.test(t)) {f2 = true;}
            if (f1 && f2) {return true;}
        }
        return false;
    }

    @FunctionalInterface
    interface TriPredicate<A, B, C>
    {
        boolean test(A a, B b, C c);
    }

}
