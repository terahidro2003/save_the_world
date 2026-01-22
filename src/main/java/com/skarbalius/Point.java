package com.skarbalius;

public class Point {
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Point
{
    double x;
    double y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistance(Point p)
    {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
