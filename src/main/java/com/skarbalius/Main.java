package com.skarbalius;

import com.skarbalius.task.CMVHandler;
import com.skarbalius.task.TaskContext;
import com.skarbalius.task.TaskHandler;

public class Main {
    public static void main(String[] args) {
        TaskContext context = new TaskContext();
        TaskHandler handler = new CMVHandler();
        handler.handle(context);
    }
}