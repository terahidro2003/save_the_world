package com.skarbalius.task;

public class FUVHandler extends TaskHandler{

    @Override
    public void handle(TaskContext context) {
        context.fuv = new java.util.Vector<>(context.NUMPOINTS);
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