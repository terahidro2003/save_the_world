package com.skarbalius;

import com.skarbalius.input.Input;
import com.skarbalius.input.InputFileParser;
import com.skarbalius.output.OutputWriter;
import com.skarbalius.task.CMVHandler;
import com.skarbalius.task.TaskContext;
import com.skarbalius.task.TaskHandler;

import java.io.File;

public class DECIDE {
    public static void execute(File inputFile, File outputFile) {
        System.out.println("DECIDE: Balistic Missile Defense System Activated.");
        System.out.println("Reading input file: " + inputFile.getAbsolutePath());
        Input input = InputFileParser.readInputFile(inputFile);
        System.out.println("Executing DECIDE algorithm...");
        TaskContext context = input.toTaskContext();
        TaskHandler handler = new CMVHandler();
        handler.handle(context);
        System.out.println("DECIDE execution completed. Writing output...");
        OutputWriter.write(outputFile, context.toOutput());
        System.out.println("Output written to: " + outputFile.getAbsolutePath());
        System.out.println("Final Launch Decision: " + (context.toOutput().decision() ? "YES" : "NO"));
    }
}
