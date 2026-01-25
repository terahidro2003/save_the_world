package com.skarbalius;

import com.skarbalius.logic.LICCalculator;
import com.skarbalius.logic.MatrixProcessor;
import com.skarbalius.model.InputData;

public class Decide {

    private LICCalculator licCalculator = new LICCalculator();
    private MatrixProcessor matrixProcessor = new MatrixProcessor();

    public void DECIDE(InputData input) {
        // 1. calculate CMV 
        boolean[] cmv = licCalculator.calculateAllLICs(input);

        // 2. calc PUM 
        boolean[][] pum = matrixProcessor.calculatePUM(cmv, input.getLcm());

        // 3. calc FUV 
        boolean[] fuv = matrixProcessor.calculateFUV(pum, input.getPuv());

        // 4. lauch decision
        matrixProcessor.makeLauchDecision(fuv)
    }
}