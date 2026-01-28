package com.skarbalius.task;

public class FinalDecisionHandler extends TaskHandler{
    @Override
    public void handle(TaskContext context) {
        context.decision = false;
        System.out.println("FinalDecisionHandler");
        super.next(context);
        context.decision = false;

        if (!context.fuv.contains(false)) {
            context.decision = true;
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
        System.out.println("FinalDecisionHandler finished");
    }
}
