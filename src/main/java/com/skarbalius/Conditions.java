package com.skarbalius;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import static com.skarbalius.GeometryPredicates.*;
import static com.skarbalius.PointWindows.*;

public class Conditions
{

    private final Vector<Boolean> cmv;

    public Conditions(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        cmv = new Vector<>(15);
        cmv.add(condition0(points, parameters));
        cmv.add(condition1(points, parameters));
        cmv.add(condition2(points, parameters));
    }

    public Vector<Boolean> getCMV() {
        return cmv;
    }

    public boolean condition0(ArrayList<Point> points, Parameters_T parameters) {
        return anyConsecutivePair(points, (p1, p2) -> p1.getDistance(p2) > parameters.LENGTH1);
    }

    public boolean condition1(ArrayList<Point> points, Parameters_T parameters) {
        return anyConsecutiveTriple(points,
                                    (p1, p2, p3) -> anyOutsideRadiusFromCentroid(p1, p2, p3, parameters.RADIUS1));
    }

    public boolean condition2(ArrayList<Point> points, Parameters_T parameters) {
        return anyConsecutiveTriple(points, (p1, p2, p3) -> angleNotWithinEpsilonOfPi(p1, p2, p3, parameters.EPSILON),
                                    (p1, p2, p3) -> p2.equals(p1) || p2.equals(p3));
    }

    public boolean condition3(ArrayList<Point> points, Parameters_T parameters) {
        return anyConsecutiveTriple(points, (p1, p2, p3) -> Point.getAreaOfTriangle(p1, p2, p3) > parameters.AREA1);
    }

    public boolean condition4(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters, int QUADS) {
        for (int i = parameters.Q_PTS; i <= NUMPOINTS; i++) {
            ArrayList<Double> quadArray = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0));
            int uniqueQuad = 0;
            for (int j = i - parameters.Q_PTS; j < i; j++) {
                double quad = points.get(j).getQuadrant();
                if (quadArray.contains(quad)) {
                    quadArray.remove(quad);
                    uniqueQuad++;
                    continue;
                }
            }
            if (uniqueQuad > QUADS) {return true;}
        }
        return false;
    }

    public boolean condition5(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        for (int i = 0; i < NUMPOINTS - 1; i++) {
            Point point1 = points.get(i);
            Point point2 = points.get(i + 1);

            if (point2.x - point1.x < 0) {return true;}
        }
        return false;
    }

    public boolean condition6(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters, double DIST) {

        if (NUMPOINTS < 3) return false;

        for (int i = parameters.N_PTS; i <= NUMPOINTS; i++) {
            
            Point point1 = points.get(i - parameters.N_PTS);
            Point point2 = points.get(i - 1);

            if (point1.x == point2.x && point1.y == point2.y) {
                for (int j = i - parameters.N_PTS + 1; j < i - 1; j++) {
                    
                    Point point3 = points.get(j);
                    double distance = point3.getDistance(point1);
                    if (distance > DIST) return true;
                }
            } else {
                
                double lineDistance = point2.getDistance(point1);

                for (int j = i - parameters.N_PTS + 1; j < i - 1; j++) {
                    
                    Point point3 = points.get(j);
                    double dj = point3.getStraightLineDistance(point1, point2, lineDistance);

                    if (dj > DIST) return true;
                }
            }
        }
        return false;
    }

    public boolean condition7(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters, double LENGTH1) {

        if (NUMPOINTS < 3) { return false; }

        for (int i = 0; i < NUMPOINTS - parameters.K_PTS - 1; i++) {

            Point point1 = points.get(i);
            Point point2 = points.get(i + parameters.K_PTS + 1);

            double distance = point1.getDistance(point2);

            if (distance > LENGTH1) {
                return true;
            }
        }
        return false;
    }

    public boolean condition8(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        if (NUMPOINTS < 5) { return false; }

        int A_PTS = parameters.A_PTS;
        int B_PTS = parameters.B_PTS;

        for (int i = 0; i < NUMPOINTS - A_PTS - B_PTS - 2; i++) {

            Point p1 = points.get(i);
            Point p2 = points.get(i + A_PTS + 1);
            Point p3 = points.get(i + A_PTS + B_PTS + 2);

            double a = p1.getDistance(p2);
            double b = p2.getDistance(p3);
            double c = p3.getDistance(p1);

            double area = Point.getAreaOfTriangle(p1, p2, p3);

            double requiredRadius;

            if (area == 0) {
                double maxDist = Math.max(a, Math.max(b, c));
                requiredRadius = maxDist / 2.0;
            } else {
                requiredRadius = (a * b * c) / (4.0 * area);
            }

            if (requiredRadius > parameters.RADIUS1) {
                return true;
            }
        }
        return false;
    }


    public boolean condition9(ArrayList<Point> points, Parameters_T parameters) {
        return anySeparatedTriple(points, parameters.C_PTS, parameters.D_PTS, (v1, v2, v3) -> {
            double angle = v2.getAngleBetweenNeighbours(v1, v3);
            return angle < Math.PI - parameters.EPSILON || angle > Math.PI + parameters.EPSILON;
        }, (v1, v2, v3) -> v2.equals(v1) || v2.equals(v3));
    }

    public boolean condition10(ArrayList<Point> points, Parameters_T parameters) {
        return anySeparatedTriple(points, parameters.E_PTS, parameters.F_PTS,
                                  (v1, v2, v3) -> Point.getAreaOfTriangle(v1, v2, v3) > parameters.AREA1);
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

    public boolean condition13(ArrayList<Point> points, Parameters_T parameters) {
        var triples = separatedTriples(points, parameters.A_PTS, parameters.B_PTS);
        return existsBoth(triples, t -> anyOutsideRadiusFromCentroid(t.a(), t.b(), t.c(), parameters.RADIUS1),
                          t -> allWithinRadiusFromCentroid(t.a(), t.b(), t.c(), parameters.RADIUS2));
    }

    public boolean condition14(ArrayList<Point> points, Parameters_T parameters) {
        var triples = separatedTriples(points, parameters.E_PTS, parameters.F_PTS);
        return existsBoth(triples, t -> Point.getAreaOfTriangle(t.a(), t.b(), t.c()) > parameters.AREA1,
                          t -> Point.getAreaOfTriangle(t.a(), t.b(), t.c()) < parameters.AREA2);
    }

}
