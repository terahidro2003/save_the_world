package com.skarbalius;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.io.Serializable;

public class Point implements Serializable
{
    double x;
    double y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public double getDistance(Point p)
    {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double getAngleBetweenNeighbours(Point p1, Point p2) {
        Vector2D v1 = new Vector2D(p1.x - this.x, p1.y - this.y);
        Vector2D v2 = new Vector2D(p2.x - this.x, p2.y - this.y);
        return Vector2D.angle(v1, v2);
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
    public boolean equals(Object o)
    {
        if (!(o instanceof Point p)) {
            return false;
        }
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode()
    {
        return Double.valueOf(x).hashCode() * 31 + Double.valueOf(y).hashCode();
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}