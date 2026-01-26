package com.skarbalius.task;

import org.junit.jupiter.api.Test;

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

        TaskHandler cmvHandler = new CMVHandler();
        cmvHandler.handle(context);
        context = cmvHandler.getContext();

        assertNotNull(context.cmv);
        assertNotNull(context.pum);
        assertNotNull(context.fuv);
        assertNotNull(context.decision);
    }
}
