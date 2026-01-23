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

    public boolean condition2(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        for (int i = 2; i < NUMPOINTS; i++) {
            Point vertex = points.get(i - 1);
            Point first_point = points.get(i - 2);
            Point last_point = points.get(i);

            if (vertex == first_point || vertex == last_point) {
                return false;
            }

            double angle = vertex.getAngleBetweenNeighbours(first_point, last_point);
            if (angle < Math.PI - parameters.EPSILON || angle > Math.PI + parameters.EPSILON) {
                return true;
            }
        }
        return false;
    }

    public boolean condition3(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        if (NUMPOINTS < 3) return false;
        
        for(int i = 2; i < NUMPOINTS; i++) { 
            
            Point p1 = points.get(i - 2);
            Point p2 = points.get(i - 1);
            Point p3 = points.get(i);

            // Calculate the area of the triangle with Heron's formula
            double a = p1.getDistance(p2);
            double b = p2.getDistance(p3);
            double c = p3.getDistance(p1);
            double s = (a + b + c) / 2.0;
            double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

            if (area > parameters.AREA1) {
                return true;
            }
        }
        return false;
    }

    public boolean condition10(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        int num_first_interv_pts = parameters.E_PTS;
        int num_second_interv_pts = parameters.F_PTS;
        if (NUMPOINTS < 5
                || num_first_interv_pts < 1
                || num_second_interv_pts < 1
                || num_first_interv_pts + num_second_interv_pts > NUMPOINTS - 3) {
            return false;
        }

        for (int i = num_first_interv_pts + num_second_interv_pts + 2; i < NUMPOINTS; i++) {
            Point v1 = points.get(i - num_second_interv_pts - num_first_interv_pts - 2);
            Point v2 = points.get(i - num_second_interv_pts - 1);
            Point v3 = points.get(i);

            // Calculate the area of the triangle with Heron's formula
            double a = v1.getDistance(v2);
            double b = v2.getDistance(v3);
            double c = v3.getDistance(v1);
            double s = (a + b + c) / 2.0;
            double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

            if (area > parameters.AREA1) {
                return true;
            }
        }
        return false;
    }

    public boolean condition11(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        int interv_pts = parameters.G_PTS;
        if (NUMPOINTS < 3 || interv_pts < 1 || interv_pts > NUMPOINTS - 2) {
            return false;
        }
        for (int i = interv_pts + 1; i < NUMPOINTS; i++) {
            Point start_point = points.get(i - interv_pts - 1);
            Point end_point = points.get(i);
            if (end_point.x - start_point.x < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean condition12(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        int interv_pts = parameters.K_PTS;
        if (NUMPOINTS < 3) {
            return false;
        }

        boolean found_l1 = false;
        boolean found_l2 = false;

        for (int i = interv_pts + 1; i < NUMPOINTS; i++) {
            Point start_point = points.get(i - interv_pts - 1);
            Point end_point = points.get(i);
            double distance = start_point.getDistance(end_point);
            if (!found_l1 && distance > parameters.LENGTH1) {
                found_l1 = true;
            }
            if (!found_l2 && distance < parameters.LENGTH2) {
                found_l2 = true;
            }
        }
        return found_l1 && found_l2;
    }

}
