package com.skarbalius;

import java.util.ArrayList;
import java.util.Arrays;
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

            if (vertex.equals(first_point) || vertex.equals(last_point)) {
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
        if (NUMPOINTS < 3) {return false;}

        for (int i = 2; i < NUMPOINTS; i++) {

            Point p1 = points.get(i - 2);
            Point p2 = points.get(i - 1);
            Point p3 = points.get(i);

            if (Point.getArea(p1, p2, p3) > parameters.AREA1) {
                return true;
            }
        }
        return false;
    }

    public boolean condition4(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters, int QUADS) {
        for (int i = parameters.Q_PTS; i <= NUMPOINTS; i++) {
            ArrayList<Integer> quadArray = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            int uniqueQuad = 0;
            for (int j = i - parameters.Q_PTS; j < i; j++) {
                if (points.get(j).x == 0) {
                    if (points.get(j).y >= 0 && quadArray.contains(1)) {
                        quadArray.remove(Integer.valueOf(1));
                        uniqueQuad++;
                        continue;
                    }
                    if (points.get(j).y < 0 && quadArray.contains(3)) {
                        quadArray.remove(Integer.valueOf(3));
                        uniqueQuad++;
                        continue;
                    }
                }
                if (points.get(j).y == 0) {
                    if (points.get(j).x >= 0 && quadArray.contains(1)) {
                        quadArray.remove(Integer.valueOf(1));
                        uniqueQuad++;
                        continue;
                    }
                    if (points.get(j).x < 0 && quadArray.contains(2)) {
                        quadArray.remove(Integer.valueOf(2));
                        uniqueQuad++;
                        continue;
                    }
                }
                if (points.get(j).x > 0 && points.get(j).y > 0 && quadArray.contains(1)) {
                    quadArray.remove(Integer.valueOf(1));
                    uniqueQuad++;
                    continue;
                }
                if (points.get(j).x < 0 && points.get(j).y > 0 && quadArray.contains(2)) {
                    quadArray.remove(Integer.valueOf(2));
                    uniqueQuad++;
                    continue;
                }
                if (points.get(j).x < 0 && points.get(j).y < 0 && quadArray.contains(3)) {
                    quadArray.remove(Integer.valueOf(3));
                    uniqueQuad++;
                    continue;
                }
                if (points.get(j).x > 0 && points.get(j).y < 0 && quadArray.contains(4)) {
                    quadArray.remove(Integer.valueOf(4));
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

        if (NUMPOINTS < 3) {return false;}

        for (int i = parameters.N_PTS; i <= NUMPOINTS; i++) {
            Point point1 = points.get(i - parameters.N_PTS);
            Point point2 = points.get(i - 1);

            if (point1.x == point2.x && point1.y == point2.y) {
                for (int j = i - parameters.N_PTS + 1; j < i - 1; j++) {
                    Point point3 = points.get(j);

                    double dx = point3.x - point1.x;
                    double dy = point3.y - point1.y;

                    double distance = Math.sqrt(dx * dx + dy * dy);

                    if (distance > DIST) {
                        return true;
                    }
                }
            } else {
                double dx = point2.x - point1.x;
                double dy = point2.y - point1.y;

                double lineDistance = Math.sqrt(dx * dx + dy * dy);


                for (int j = i - parameters.N_PTS + 1; j < i - 1; j++) {
                    Point point3 = points.get(j);
                    double dj = Math.abs(((dy * point3.x) - (dx * point3.y) + point2.x * point1.y - point2.y * point1.x) / lineDistance);

                    if (dj > DIST) {
                        return true;
                    }
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

            double dx = point2.x - point1.x;
            double dy = point2.y - point1.y;

            double distance = Math.sqrt(dx * dx + dy * dy);

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

        double area = Point.getArea(p1, p2, p3);

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


    public boolean condition9(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        int first_interv_pts = parameters.C_PTS;
        int second_interv_pts = parameters.D_PTS;
        if (NUMPOINTS < 5
                || first_interv_pts < 1
                || second_interv_pts < 1
                || first_interv_pts + second_interv_pts > NUMPOINTS - 3) {
            return false;
        }
        // TODO: Reduce duplication with condition 2, 10, 1 & 12
        // Literally the same as condition 2 but with intervening points
        for (int i = first_interv_pts + second_interv_pts + 2; i < NUMPOINTS; i++) {
            Point v1 = points.get(i - second_interv_pts - first_interv_pts - 2);
            Point v2 = points.get(i - second_interv_pts - 1);
            Point v3 = points.get(i);
            if (v2.equals(v1) || v2.equals(v3)) {
                return false;
            }
            double angle = v2.getAngleBetweenNeighbours(v1, v3);
            if (angle < Math.PI - parameters.EPSILON || angle > Math.PI + parameters.EPSILON) {
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

            if (Point.getArea(v1, v2, v3) > parameters.AREA1) {
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

    public boolean condition13(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        int first_interv_pts = parameters.A_PTS;
        int second_interv_pts = parameters.B_PTS;
        if (NUMPOINTS < 5 || parameters.RADIUS2 < 0) {
            return false;
        }
        // TODO: Reduce duplication with condition10, 1 & 12
        boolean found_s1 = false;
        boolean found_s2 = false;
        for (int i = first_interv_pts + second_interv_pts + 2; i < NUMPOINTS; i++) {
            Point v1 = points.get(i - second_interv_pts - first_interv_pts - 2);
            Point v2 = points.get(i - second_interv_pts - 1);
            Point v3 = points.get(i);
            ArrayList<Point> curr_points = new ArrayList<Point>(Arrays.asList(v1, v2, v3));
            final Point center = Point.getCentroid(curr_points);
            if (!found_s1) {
                found_s1 = curr_points.stream()
                        .anyMatch(point -> point.getDistance(center) > parameters.RADIUS1);
            }
            if (!found_s2) {
                found_s2 = curr_points.stream()
                        .allMatch(point -> point.getDistance(center) <= parameters.RADIUS2);
            }
        }
        return found_s1 && found_s2;
    }

    public boolean condition14(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters) {
        int first_interv_pts = parameters.E_PTS;
        int second_interv_pts = parameters.F_PTS;
        if (NUMPOINTS < 5 || parameters.AREA2 < 0) {
            return false;
        }
        // TODO: Reduce duplication with condition10, 1, 12, 13
        boolean found_s1 = false;
        boolean found_s2 = false;
        for (int i = first_interv_pts + second_interv_pts + 2; i < NUMPOINTS; i++) {
            Point v1 = points.get(i - second_interv_pts - first_interv_pts - 2);
            Point v2 = points.get(i - second_interv_pts - 1);
            Point v3 = points.get(i);
            if (!found_s1) {
                found_s1 = Point.getArea(v1, v2, v3) > parameters.AREA1;
            }
            if (!found_s2) {
                found_s2 = Point.getArea(v1, v2, v3) < parameters.AREA2;
            }
        }
        return found_s1 && found_s2;
    }

}
