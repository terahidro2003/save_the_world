package com.skarbalius.task;

import org.junit.jupiter.api.Test;

import com.skarbalius.LICTests.TestParameters;

import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;

class LaunchTest {

    @Test
    void finaldecisionlaunch() {
        TaskContext context = new TaskContext();
        context.NUMPOINTS = 10;
        context.parameters = new TestParameters();
        context.puv = new Vector<>(15);
        context.pum = new Vector<>(15);

        for (int i = 0; i < 15; i++) {
            // Mock PUV data
            for (int j = 0; j < 14; j++){
                context.puv.add(true);
            }
            context.puv.add(false);

            // Mock PUM data
            Vector<Boolean> pumRow = new Vector<>(15);
            for (int j = 0; j < 15; j++){
                    pumRow.add(true);
            }
            context.pum.add(pumRow);
        }

        // Verify decision is set to true when all PUM[i]=true and PUV[i]=true.
        // PUV[15]= false while PUM[15]= true should give FUV[15]=true.
        // Decision should be set to true.
        TaskHandler fuvHandler = new FUVHandler();
        fuvHandler.handle(context);
        context = fuvHandler.getContext();
        assertTrue(context.decision);
    }

    @Test
    void finaldecisionnotlaunch2() {
        TaskContext context = new TaskContext();
        context.NUMPOINTS = 10;
        context.parameters = new TestParameters();
        context.puv = new Vector<>(15);
        context.pum = new Vector<>(15);

        for (int i = 0; i < 15; i++) {
            // Mock  PUV data
            for (int j = 0; j < 15; j++){
                context.puv.add(true);
            }

            // Mock PUM data
            Vector<Boolean> pumRow = new Vector<>(15);
            for (int j = 0; j < 15; j++){
                if (i % (j+1) == 0) {
                    pumRow.add(true);
                }
                else {
                    pumRow.add(false);
                }
            }
            context.pum.add(pumRow);
        }

        // Verify that it's false since PUV[i]=true, but matching PUM[i]=false.
        // Decision should be set to false.
        TaskHandler fuvHandler = new FUVHandler();
        fuvHandler.handle(context);
        context = fuvHandler.getContext();
        assertFalse(context.decision);
    }

    @Test
    void finaldecisionnotlaunch() {
        TaskContext context = new TaskContext();
        context.NUMPOINTS = 10;
        context.parameters = new TestParameters();
        context.puv = new Vector<>(15);
        context.pum = new Vector<>(15);

        for (int i = 0; i < 15; i++) {
            // Mock  PUV data
            for (int j = 0; j < 15; j++){
                context.puv.add(false);
            }

            // Mock PUM data
            Vector<Boolean> pumRow = new Vector<>(15);
            for (int j = 0; j < 15; j++){
                    pumRow.add(false);
                }
            context.pum.add(pumRow);
        }

        // Verify that decision is set to true since PUV[i]=false, even if all PUM[i]=false.
        TaskHandler fuvHandler = new FUVHandler();
        fuvHandler.handle(context);
        context = fuvHandler.getContext();
        assertTrue(context.decision);
    }

}
