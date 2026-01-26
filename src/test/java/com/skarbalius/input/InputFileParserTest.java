package com.skarbalius.input;

import com.skarbalius.Parameters_T;
import com.skarbalius.Point;
import com.skarbalius.conditionTests.TestParameters;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputFileParserTest {

    private static final InputFileParser PARSER = new InputFileParser();
    private  static final File resourcesDirectory = new File("src/test/resources");

    @Test
    void write() {
        Vector<Point> points = new Vector<>();
        points.add(new Point(143098.509, 855368.983));
        points.add(new Point(-486139.466, -978699.884));
        Parameters_T parameters = new TestParameters();
        Vector<Boolean> puv = new Vector<>();
        puv.add(true);
        puv.add(false);
        var lcm = new Vector<Vector<BooleanOperator>>();
        var row1 = new Vector<BooleanOperator>();
        row1.add(BooleanOperator.ANDD);
        row1.add(BooleanOperator.ORR);
        var row2 = new Vector<BooleanOperator>();
        row2.add(BooleanOperator.NOTUSED);
        row2.add(BooleanOperator.ANDD);
        lcm.add(row1);
        lcm.add(row2);
        Input input = new Input(
                2,
                points,
                parameters,
                puv,
                lcm
        );
        File outputFile = new File(resourcesDirectory.getAbsolutePath() + "/input.json");
        PARSER.writeInputFile(outputFile, input);
        Input result = PARSER.readInputFile(outputFile);
        assertEquals(input.NUMPOINTS(), result.NUMPOINTS());
        assertEquals(input.points().size(), result.points().size());
        assertTrue(input.parameters().equals(result.parameters()));
        assertEquals(input.PUV(), result.PUV());
        assertEquals(input.LCM(), result.LCM());
    }
}
