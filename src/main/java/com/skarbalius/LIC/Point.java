package com.skarbalius.LIC;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.List;
import java.io.Serializable;

public class Point implements Serializable
{
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {}

    public static Point getCentroid(List<Point> points) {
        Point center = points.stream().reduce(new Point(0, 0), (acc, p) -> {
            acc.x += p.x;
            acc.y += p.y;
            return acc;
        });
        center.x /= 3;
        center.y /= 3;
        return center;
    }

    //TODO: Move to GeometryUtils class

    /**
     * Calculate the area of a triangle given its vertices using Heron's formula
     */
    public static double getAreaOfTriangle(Point v1, Point v2, Point v3) {
        double a = v1.getDistance(v2);
        double b = v2.getDistance(v3);
        double c = v3.getDistance(v1);
        double s = (a + b + c) / 2.0;

        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public double getDistance(Point p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getAngleBetweenNeighbours(Point p1, Point p2) {
        Vector2D v1 = new Vector2D(p1.x - this.x, p1.y - this.y);
        Vector2D v2 = new Vector2D(p2.x - this.x, p2.y - this.y);
        return Vector2D.angle(v1, v2);
    }

    public double getStraightLineDistance(Point p1, Point p2, double lineDistance) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;

        return Math.abs(
        (dy * this.x) -
        (dx * this.y) +
        (p2.x * p1.y) -
        (p2.y * p1.x))/lineDistance;
    }

    @JsonIgnore
    public int getQuadrant() {
        double x = this.x;
        double y = this.y;

        if (x == 0) {
            return (y >= 0) ? 1 : 3;
        }
        if (y == 0) {
            return (x >= 0) ? 1 : 2;
        }

        if (x > 0 && y > 0) return 1;
        if (x < 0 && y > 0) return 2;
        if (x < 0 && y < 0) return 3;
        return 4;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point p)) {
            return false;
        }
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(x).hashCode() * 31 + Double.valueOf(y).hashCode();
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}

record Triple(Point a, Point b, Point c) {}
