package com.skarbalius.input;

import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;

import java.util.Vector;

public record Input(int NUMPOINTS, Vector<Point> points, Parameters_T parameters, Vector<Boolean> PUV, Vector<Vector<BooleanOperator>> LCM) {
}
