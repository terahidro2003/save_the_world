package com.skarbalius;

import java.util.ArrayList;
import java.util.Vector;

public class Conditions
{

    private final Vector<Boolean> cmv;

    public Conditions(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        cmv = new Vector<Boolean>(15);
        cmv.add(condition0(points, NUMPOINTS, parameters));
        cmv.add(condition1(points, NUMPOINTS, parameters));
        cmv.add(condition2(points, NUMPOINTS, parameters));
    }

    public Vector<Boolean> getCMV() {
        return cmv;
    }

    public boolean condition0(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        for (int i = 1; i < NUMPOINTS; i++) {
            if (points.get(i).getDistance(points.get(i - 1)) > parameters.LENGTH1) {
                return true;
            }
        }
        return false;
    }

    public boolean condition1(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        Point center;

        for (int i = 2; i < NUMPOINTS; i++) {
            ArrayList<Point> consecutive_points = new ArrayList<Point>(3);
            for (int j = i - 2; j <= i; j++) {
                consecutive_points.add(points.get(j));
            }

            center = consecutive_points.stream()
                    .reduce(new Point(0, 0), (acc, p) -> {
                        acc.x += p.x;
                        acc.y += p.y;
                        return acc;
                    });
            center.x /= 3;
            center.y /= 3;

            Point finalCenter = center;
            boolean min_one_outside = consecutive_points.stream()
                    .anyMatch(point -> point.getDistance(finalCenter) > parameters.RADIUS1);

            if (min_one_outside) {
                return true;
            }
        }
        return false;
    }


}
