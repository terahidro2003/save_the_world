package com.skarbalius.task;

public abstract class TaskHandler {
    public TaskHandler nextHandler;

    void setNextHandler(TaskHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(TaskContext context);

    protected void next(TaskContext context) {
        if (nextHandler != null) {
            nextHandler.handle(context);
        }
    }
}
