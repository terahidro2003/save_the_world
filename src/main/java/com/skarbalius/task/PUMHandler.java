package com.skarbalius.task;

public class PUMHandler extends TaskHandler{
    @Override
    public void handle(TaskContext context) {
        context.pum = new java.util.Vector<>(context.NUMPOINTS);
        System.out.println("PUMHandler");
        super.setNextHandler(new FUVHandler());
        super.next(context);
    }
}
