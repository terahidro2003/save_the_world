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
}
