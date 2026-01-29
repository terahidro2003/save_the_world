package com.skarbalius.task;

import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import com.skarbalius.input.BooleanOperator;
import com.skarbalius.output.Output;

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

    public TaskContext(ArrayList<Point> points, int NUMPOINTS, Parameters_T parameters, Vector<Vector<BooleanOperator>> lcm, Vector<Boolean> puv) {
        this.points = points;
        this.NUMPOINTS = NUMPOINTS;
        this.parameters = parameters;
        this.lcm = lcm;
        this.puv = puv;
    }

    public TaskContext() {
    }

    public Output toOutput() {
        return new Output(cmv, pum, fuv, decision);
    }
}
