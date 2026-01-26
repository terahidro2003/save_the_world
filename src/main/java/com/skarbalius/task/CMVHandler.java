package com.skarbalius.task;

public class CMVHandler extends TaskHandler{
    @Override
    public void handle(TaskContext context) {
        context.cmv = new java.util.Vector<>(context.NUMPOINTS);
        System.out.println("CMVHandler");
        super.setNextHandler(new PUMHandler());
        super.next(context);
    }
}
