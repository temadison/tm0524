package com.temadison.rental.tool;

import com.temadison.rental.tool.data.model.ToolMO;
import com.temadison.rental.tool.repository.ToolRepository;
import com.temadison.rental.tool.util.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDatabase.class);

    /**
     * Initialize database using tool records from 'load_database.json' file.
     *
     * @param toolRepository
     * @return
     */
    @Bean
    CommandLineRunner initDatabase(ToolRepository toolRepository) {
        LOGGER.info("Removing existing tool records...");
        toolRepository.deleteAll();
        LOGGER.info("Existing tool records removed.");

        LOGGER.info("Loading new tool records...");
        JsonParser parser = new JsonParser();
        InputStream is = getClass().getClassLoader().getResourceAsStream("load_database.json");
        List<ToolMO> tools = parser.parseJsonFile(is);
        return args -> {
            toolRepository.saveAll(tools);
            LOGGER.info("New tool records loaded.");
        };
    }
}
