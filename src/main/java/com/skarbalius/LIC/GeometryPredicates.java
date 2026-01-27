package com.skarbalius.LIC;

import java.util.Arrays;
import java.util.List;

public class GeometryPredicates
{
    static boolean angleNotWithinEpsilonOfPi(Point p1, Point p2, Point p3, double eps) {
        double angle = p2.getAngleBetweenNeighbours(p1, p3);
        return angle < Math.PI - eps || angle > Math.PI + eps;
    }

    static boolean anyOutsideRadiusFromCentroid(Point p1, Point p2, Point p3, double radius) {
        List<Point> points = Arrays.asList(p1, p2, p3);
        Point center = Point.getCentroid(points);
        return points.stream().anyMatch(p -> p.getDistance(center) > radius);
    }

    static boolean allWithinRadiusFromCentroid(Point p1, Point p2, Point p3, double radius) {
        Point center = Point.getCentroid(Arrays.asList(p1, p2, p3));
        return p1.getDistance(center) <= radius && p2.getDistance(center) <= radius && p3.getDistance(center) <= radius;
    }
}
