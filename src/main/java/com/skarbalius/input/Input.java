package com.skarbalius.input;

import com.skarbalius.LIC.Parameters_T;
import com.skarbalius.LIC.Point;
import com.skarbalius.task.TaskContext;

import java.util.Vector;

public record Input(int NUMPOINTS, Vector<Point> points, Parameters_T parameters, Vector<Boolean> PUV, Vector<Vector<BooleanOperator>> LCM) {
    public TaskContext toTaskContext() {
        return new TaskContext(new java.util.ArrayList<>(points), NUMPOINTS, parameters, LCM, PUV);
    }
}
