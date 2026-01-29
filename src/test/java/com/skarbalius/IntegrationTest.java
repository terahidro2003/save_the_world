package com.skarbalius;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skarbalius.output.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTest {
    private static final File resourcesDirectory = new File("src/test/resources");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        if (!resourcesDirectory.exists()) {
            resourcesDirectory.mkdirs();
        }
    }

    @Test
    @DisplayName("Testcase 1: YES (LICs: 0,2,4,6,9,12 satisfied)")
    void testcase1() throws IOException {
        DECIDE.execute(
                new File(resourcesDirectory + "/testcase_1.json"),
                new File(resourcesDirectory + "/output_testcase_1.json")
        );

        var result = objectMapper.readValue(new File(resourcesDirectory + "/output_testcase_1.json"), Output.class);
        assertNotNull(result);
        assertNotNull(result.cmv());
        assertTrue(result.cmv().get(0));
        assertTrue(result.cmv().get(2));
        assertTrue(result.cmv().get(4));
        assertTrue(result.cmv().get(6));
        assertTrue(result.cmv().get(9));
        assertTrue(result.cmv().get(12));

        assertTrue(result.decision());
    }

    @Test
    @DisplayName("Testcase 2: NO (minimal NUMPOINTS=2, forced NO via PUV+LCM ANDD)")
    void testcase2() throws IOException {
        DECIDE.execute(
                new File(resourcesDirectory + "/testcase_2.json"),
                new File(resourcesDirectory + "/output_testcase_2.json")
        );

        var result = objectMapper.readValue(new File(resourcesDirectory + "/output_testcase_2.json"), Output.class);
        assertNotNull(result);
        assertNotNull(result.cmv());

        // With only 2 points:
        // - LIC0 requires consecutive distance > LENGTH1 (false here: sqrt(2) <= 5)
        // - LIC5 requires X[j]-X[i] < 0 for consecutive points (false here: 1-0 > 0)
        // - LIC6/7/8/9/10/11/12/13/14 are not met due to NUMPOINTS constraints in the spec
        assertFalse(result.cmv().get(0));
        assertFalse(result.cmv().get(5));
        assertFalse(result.cmv().get(6));
        assertFalse(result.cmv().get(7));
        assertFalse(result.cmv().get(8));
        assertFalse(result.cmv().get(9));
        assertFalse(result.cmv().get(10));
        assertFalse(result.cmv().get(11));
        assertFalse(result.cmv().get(12));
        assertFalse(result.cmv().get(13));
        assertFalse(result.cmv().get(14));

        // PUV[0] is true, and LCM[0][1] is ANDD, so row 0 cannot be all true => LAUNCH must be false
        assertFalse(result.decision());
    }

    @Test
    @DisplayName("Testcase 3: YES (broad LIC coverage incl. 12–14, mixed LCM ANDD/ORR)")
    void testcase3() throws IOException {
        DECIDE.execute(
                new File(resourcesDirectory + "/testcase_3.json"),
                new File(resourcesDirectory + "/output_testcase_3.json")
        );

        var result = objectMapper.readValue(new File(resourcesDirectory + "/output_testcase_3.json"), Output.class);
        assertNotNull(result);
        assertNotNull(result.cmv());

        // Designed to trigger many different LIC families:
        // Distances, circle containment, triangle area, quadrants, line distance, and combined LICs 12–14.
        assertTrue(result.cmv().get(0));   // consecutive far-apart pair exists
        assertTrue(result.cmv().get(1));   // 3 consecutive points not in circle radius RADIUS1
        assertTrue(result.cmv().get(3));   // triangle area > AREA1
        assertTrue(result.cmv().get(4));   // Q_PTS points span > QUADS quadrants
        assertTrue(result.cmv().get(5));   // consecutive x decreases somewhere
        assertTrue(result.cmv().get(6));   // point far from line within N_PTS window
        assertTrue(result.cmv().get(7));   // separated-by-K_PTS far-apart pair exists
        assertTrue(result.cmv().get(10));  // separated-by-(E,F) triangle area > AREA1
        assertTrue(result.cmv().get(11));  // separated-by-G_PTS x decreases
        assertTrue(result.cmv().get(12));  // both > LENGTH1 and < LENGTH2 exist for separated-by-K_PTS
        assertTrue(result.cmv().get(13));  // both (not in RADIUS1) and (in RADIUS2) exist for separated-by-(A,B)
        assertTrue(result.cmv().get(14));  // both > AREA1 and < AREA2 exist for separated-by-(E,F)

        assertTrue(result.decision());
    }
}
