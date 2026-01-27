package com.skarbalius.task;

import com.skarbalius.LIC.Conditions;

public class CMVHandler extends TaskHandler{

    @Override
    public void handle(TaskContext context) {
        final var delegate = new Conditions(context.points, context.parameters);
        context.cmv = delegate.getCMV();
        System.out.println("CMVHandler finished");
        super.setNextHandler(new PUMHandler());
        super.next(context);
    }
}
