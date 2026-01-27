package com.skarbalius.task;

import com.skarbalius.LIC.Point;
import com.skarbalius.conditionTests.TestParameters;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaskPipelineTest {

    @Test
    void test() {
        TaskContext context = new TaskContext();
        assertNull(context.cmv);
        assertNull(context.pum);
        assertNull(context.fuv);
        assertNull(context.decision);
        context.NUMPOINTS = 10;
        context.parameters = new TestParameters();
        context.points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 5),
                new Point(10, 5),  // Large triangle
                new Point(2, 2),
                new Point(10, 4.5)  // Small triangle
        ));

        TaskHandler cmvHandler = new CMVHandler();
        cmvHandler.handle(context);
        context = cmvHandler.getContext();

        assertNotNull(context.cmv);
        assertNotNull(context.pum);
        assertNotNull(context.fuv);
        assertNotNull(context.decision);
    }
}
