package com.temadison.rental.tool.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.temadison.rental.tool.model.ToolMO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);

    private final ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<ToolMO> parseJsonFile(InputStream is) {
        try {
            Map<String, List<ToolMO>> data = objectMapper.readValue(
                    is,
                    new TypeReference<Map<String, List<ToolMO>>>() {
                    }
            );

            return data.get("toolMOList");
        } catch (IOException e) {
            LOGGER.error("Unable to parse JSON file '" + is + "'", e);
            return null;
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
