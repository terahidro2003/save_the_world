package com.skarbalius;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar DECIDE.jar <inputFilePath> <outputFilePath>");
            return;
        }

        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        DECIDE.execute(inputFile, outputFile);
    }
}