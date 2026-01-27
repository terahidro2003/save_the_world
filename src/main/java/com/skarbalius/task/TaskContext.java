package com.skarbalius.task;

import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import com.skarbalius.input.BooleanOperator;

import java.util.ArrayList;
import java.util.Vector;

public class TaskContext {
    ArrayList<Point> points;
    int NUMPOINTS;
    Parameters_T parameters;
    Vector<Boolean> cmv;

    Vector<Vector<BooleanOperator>> lcm;
    Vector<Vector<Boolean>> pum;

    Vector<Boolean> puv;
    Vector<Boolean> fuv;

    Boolean decision;
}
