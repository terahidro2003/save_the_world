package com.skarbalius.task;

public class FinalDecisionHandler extends TaskHandler{
    @Override
    public void handle(TaskContext context) {
        context.decision = false;
        System.out.println("FinalDecisionHandler");
        super.next(context);
    }
}
