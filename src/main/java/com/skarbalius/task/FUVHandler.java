package com.skarbalius.task;

public class FUVHandler extends TaskHandler{
    @Override
    public void handle(TaskContext context) {
        context.fuv = new java.util.Vector<>(context.NUMPOINTS);
        System.out.println("FUVHandler");
        super.setNextHandler(new FinalDecisionHandler());
        super.next(context);
    }
}
