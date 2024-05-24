package com.temadison.rental.tool.util;

import com.temadison.rental.tool.data.model.ToolMO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JsonParserTest {
    JsonParser parser = new JsonParser();

    InputStream is;

    @BeforeEach
    void setUp() {
        is = getClass().getClassLoader().getResourceAsStream("load_database.json");
    }

    @AfterEach
    void tearDown() throws IOException {
        if (is != null) {
            is.close();
        }
    }

    @Test
    void parseJsonFile() {
        List<ToolMO> toolList = parser.parseJsonFile(is);

        assertNotNull(toolList);
        assertEquals(4, toolList.size());
    }
}
