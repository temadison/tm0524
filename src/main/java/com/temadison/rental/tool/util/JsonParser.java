package com.temadison.rental.tool.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.temadison.rental.tool.data.model.ToolMO;
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

    /**
     * Parse an input stream containing tool data in JSON format.
     *
     * @param inputStream
     * @return A list of ToolMO records.
     */
    public List<ToolMO> parseJsonFile(InputStream inputStream) {
        try {
            Map<String, List<ToolMO>> data = objectMapper.readValue(
                    inputStream,
                    new TypeReference<Map<String, List<ToolMO>>>() {
                    }
            );

            return data.get("toolMOList");
        } catch (IOException e) {
            LOGGER.error("Unable to parse JSON file '" + inputStream + "'", e);
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
