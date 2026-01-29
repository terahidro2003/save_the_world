package com.skarbalius.task;

import com.skarbalius.LIC.Point;
import com.skarbalius.LICTests.TestParameters;

import org.junit.jupiter.api.Test;
import com.skarbalius.input.BooleanOperator;
import java.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class TaskPipelineTest {

    @Test
    void test() {
        TaskContext context = new TaskContext();
        assertNull(context.cmv);
        assertNull(context.pum);
        assertNull(context.fuv);
        assertNull(context.decision);
        context.NUMPOINTS = 10;
        context.parameters = new TestParameters();
        context.points = new ArrayList<>(Arrays.asList(
                new Point(0, 0),
                new Point(1, 5),
                new Point(10, 5),  // Large triangle
                new Point(2, 2),
                new Point(10, 4.5)  // Small triangle
        ));
        context.lcm = new Vector<>(15);
        for (int i = 0; i < 15; i++) {
            Vector<BooleanOperator> row = new Vector<>(15);
            for (int j = 0; j < 15; j++) row.add(BooleanOperator.NOTUSED);
            context.lcm.add(row);
        }

         // Mock PUV matrix 
        context.puv = new Vector<>(15);
        for (int j = 0; j < 14; j++){
            context.puv.add(true);
        }
        context.puv.add(false);

        TaskHandler cmvHandler = new CMVHandler();
        cmvHandler.handle(context);
        context = cmvHandler.getContext();

        assertNotNull(context.cmv);
        assertNotNull(context.pum);
        assertNotNull(context.fuv);
        assertNotNull(context.decision);
    }

    @Test
    void testPUMHandler() {
        TaskContext context = new TaskContext();
        
        // Mock CMV data 
        context.cmv = new Vector<>(15);
        for (int i = 0; i < 15; i++) context.cmv.add(false); 
        context.cmv.set(0, true);
        context.cmv.set(1, false);
        context.cmv.set(10, true);
        context.cmv.set(11, true);

        // Mock LCM matrix 
        context.lcm = new Vector<>(15);
        for (int i = 0; i < 15; i++) {
            Vector<BooleanOperator> row = new Vector<>(15);
            for (int j = 0; j < 15; j++) row.add(BooleanOperator.NOTUSED);
            context.lcm.add(row);
        }

        // Mock PUV matrix 
        context.puv = new Vector<>(15);
        for (int j = 0; j < 14; j++){
            context.puv.add(true);
        }
        context.puv.add(false);

        context.lcm.get(0).set(1, BooleanOperator.ANDD); 
        context.lcm.get(1).set(0, BooleanOperator.ANDD);
        context.lcm.get(0).set(2, BooleanOperator.ORR);
        context.lcm.get(2).set(0, BooleanOperator.ORR);
        context.lcm.get(3).set(4, BooleanOperator.NOTUSED);
        context.lcm.get(4).set(3, BooleanOperator.NOTUSED);
        context.lcm.get(8).set(9, BooleanOperator.ORR);
        context.lcm.get(9).set(8, BooleanOperator.ORR);
        context.lcm.get(10).set(11, BooleanOperator.ANDD);
        context.lcm.get(11).set(10, BooleanOperator.ANDD);

        PUMHandler handler = new PUMHandler();
        handler.handle(context);

        // --- Assertions to ensure logic matches requirements ---
        
        // Verify ANDD logic
        assertFalse(context.pum.get(0).get(1), "True ANDD False should be False");
        assertFalse(context.pum.get(1).get(0), "True ANDD False should be False");
        assertTrue(context.pum.get(10).get(11), "True ANDD True should be True");
        assertTrue(context.pum.get(11).get(10), "True ANDD True should be True");
        
        // Verify ORR logic
        assertTrue(context.pum.get(0).get(2), "True ORR False should be True");
        assertTrue(context.pum.get(2).get(0), "True ORR False should be True");
        assertFalse(context.pum.get(8).get(9), "False ORR False should be False");
        assertFalse(context.pum.get(9).get(8), "False ORR False should be False");
        
        // Verify NOTUSED logic: must be True regardless of CMV values
        assertTrue(context.pum.get(3).get(4), "NOTUSED should always result in True");
        assertTrue(context.pum.get(4).get(3), "NOTUSED should always result in True");

        // Verify Matrix Symmetry
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertEquals(context.pum.get(i).get(j), context.pum.get(j).get(i), "PUM must be symmetric at (" + i + "," + j + ")");
            }
        }

        // Verify Matrix Dimensions (15x15)
        assertEquals(15, context.pum.size());
        assertEquals(15, context.pum.get(0).size());
    }

    @Test
    void testPUMHandler_not_symmetric_lcm() {
        TaskContext context = new TaskContext();

        // Mock CMV data
        context.cmv = new Vector<>(15);
        for (int i = 0; i < 15; i++) context.cmv.add(false);
        context.cmv.set(0, true);
        context.cmv.set(1, false);

        // Mock LCM matrix
        context.lcm = new Vector<>(15);
        for (int i = 0; i < 15; i++) {
            Vector<BooleanOperator> row = new Vector<>(15);
            for (int j = 0; j < 15; j++) row.add(BooleanOperator.NOTUSED);
            context.lcm.add(row);
        }

        context.lcm.get(0).set(1, BooleanOperator.ANDD);
        context.lcm.get(1).set(0, BooleanOperator.ORR);
        context.lcm.get(0).set(2, BooleanOperator.ORR);
        context.lcm.get(2).set(0, BooleanOperator.NOTUSED);

        PUMHandler handler = new PUMHandler();
        assertThrows(IllegalArgumentException.class, () -> handler.handle(context));
    }
}


