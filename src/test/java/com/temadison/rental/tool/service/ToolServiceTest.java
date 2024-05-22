package com.temadison.rental.tool.service;

import com.temadison.rental.tool.model.Brand;
import com.temadison.rental.tool.model.ToolMO;
import com.temadison.rental.tool.model.ToolType;
import com.temadison.rental.tool.repository.ToolRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ToolServiceTest {

    private static final String TOOL_CODE_1 = "CHNS";
    private static final ToolType TOOL_TYPE_1 = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_1 = Brand.DEWALT;
    private static final Double TOOL_DAILY_RATE_1 = 1.49;

    private static final String TOOL_CODE_2 = "LADW";
    private static final ToolType TOOL_TYPE_2 = ToolType.LADDER;
    private static final Brand TOOL_BRAND_2 = Brand.WERNER;
    private static final Double TOOL_DAILY_RATE_2 = 1.99;

    @Mock
    private ToolRepository toolRepository;

    @InjectMocks
    private ToolService toolService;

    public ToolServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test ToolService.getAllTools() works")
    public void testGetAllTools() {
        ToolMO tool1 = new ToolMO(TOOL_CODE_1, TOOL_TYPE_1, TOOL_BRAND_1, TOOL_DAILY_RATE_1);
        ToolMO tool2 = new ToolMO(TOOL_CODE_2, TOOL_TYPE_2, TOOL_BRAND_2, TOOL_DAILY_RATE_2);

        when(toolRepository.findAll()).thenReturn(Arrays.asList(tool1, tool2));

        List<ToolMO> tools = toolService.getAllTools();
        assertEquals(2, tools.size());
        assertEquals(TOOL_CODE_1, tools.get(0).getCode());
        assertEquals(TOOL_CODE_2, tools.get(1).getCode());
    }

    @Test
    @DisplayName("Test ToolService.getToolById() works when ID is found")
    public void testGetToolById() {
        ToolMO tool = new ToolMO(TOOL_CODE_1, TOOL_TYPE_1, TOOL_BRAND_1, TOOL_DAILY_RATE_1);

        when(toolRepository.findById(1L)).thenReturn(Optional.of(tool));

        ToolMO foundTool = toolService.getToolById(1L);
        assertNotNull(foundTool);
        assertEquals(TOOL_CODE_1, foundTool.getCode());
    }

    @Test
    @DisplayName("Test ToolService.getToolById() returns null when ID not found")
    public void testGetToolByIdNotFound() {
        when(toolRepository.findById(1L)).thenReturn(Optional.empty());

        ToolMO foundTool = toolService.getToolById(1L);

        assertNull(foundTool);
    }

    @Test
    @DisplayName("Test ToolService.getToolByCode() works when code is found")
    public void testGetToolByCode() {
        ToolMO tool = new ToolMO(TOOL_CODE_1, TOOL_TYPE_1, TOOL_BRAND_1, TOOL_DAILY_RATE_1);

        when(toolRepository.findFirstByCode(TOOL_CODE_1)).thenReturn(Optional.of(tool));

        ToolMO foundTool = toolService.getToolByCode(TOOL_CODE_1);
        assertNotNull(foundTool);
        assertEquals(TOOL_CODE_1, foundTool.getCode());
    }

    @Test
    @DisplayName("Test ToolService.getToolByCod() returns null when code not found")
    public void testGetToolByCodeNotFound() {
        when(toolRepository.findFirstByCode("MISSING")).thenReturn(Optional.empty());

        ToolMO foundTool = toolService.getToolByCode("MISSING");

        assertNull(foundTool);
    }
}
