package com.skarbalius.input;

import com.skarbalius.Parameters_T;
import com.skarbalius.Point;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public record Input(int NUMPOINTS, Vector<Point> points, Parameters_T parameters, Vector<Boolean> PUV, Vector<Vector<BooleanOperator>> LCM) {
}
