package com.skarbalius.task;

import com.skarbalius.input.BooleanOperator;
import java.util.Vector;

public class FUVHandler extends TaskHandler{
    private static final int SIZE = 15;

    @Override
    public void handle(TaskContext context) {
        context.fuv = new java.util.Vector<>(context.NUMPOINTS);
        super.setNextHandler(new FinalDecisionHandler());
        super.next(context);

        for (int i = 0; i < 15; i++) {
            if (context.puv.get(i) == false) {
                context.fuv.add(true);
            }
            else {
                if (context.pum.get(i).contains(false)){
                    context.fuv.add(false);
                }
                else {
                    context.fuv.add(true);
                    }
                }
            }
        System.out.println("FUVHandler finished");
        super.setNextHandler(new FinalDecisionHandler());
        super.next(context);
    }
}