package com.skarbalius.task;

public abstract class TaskHandler {
    public TaskHandler nextHandler;
    private TaskContext lastContext;

    void setNextHandler(TaskHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(TaskContext context);

    protected void next(TaskContext context) {
        this.lastContext = context;
        if (nextHandler != null) {
            nextHandler.handle(context);
        }
    }

    public TaskContext getContext() {
        return lastContext;
    }
}
