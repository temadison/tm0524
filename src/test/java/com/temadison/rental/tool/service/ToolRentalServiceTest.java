package com.temadison.rental.tool.service;

import com.temadison.rental.tool.data.*;
import com.temadison.rental.tool.data.model.ToolMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ToolRentalServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolRentalServiceTest.class);

    private static final String TOOL_CODE_CHNS = "CHNS";
    private static final ToolType TOOL_TYPE_CHNS = ToolType.CHAINSAW;
    private static final Brand TOOL_BRAND_CHNS = Brand.STIHL;

    private static final String TOOL_CODE_JAKD = "JAKD";
    private static final ToolType TOOL_TYPE_JAKD = ToolType.JACKHAMMER;
    private static final Brand TOOL_BRAND_JAKD = Brand.DEWALT;

    private static final String TOOL_CODE_JAKR = "JAKR";
    private static final ToolType TOOL_TYPE_JAKR = ToolType.JACKHAMMER;
    private static final Brand TOOL_BRAND_JAKR = Brand.RIDGID;

    private static final String TOOL_CODE_LADW = "LADW";
    private static final ToolType TOOL_TYPE_LADW = ToolType.LADDER;
    private static final Brand TOOL_BRAND_LADW = Brand.WERNER;

    @Mock
    private ToolService toolService;

    @InjectMocks
    private ToolRentalService toolRentalService;

    @BeforeEach
    void setUp() {
    }

    public ToolRentalServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkoutTest1() {
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKR, TOOL_TYPE_JAKR, TOOL_BRAND_JAKR);

        when(toolService.getToolByCode(TOOL_CODE_JAKR)).thenReturn(toolMO);

        Checkout checkout = new Checkout(toolMO.getCode(), 5, 101, LocalDate.of(2015, 9, 15));

        Exception exception = assertThrows(DataValidationException.class, () -> {
            Agreement agreement = toolRentalService.checkout(checkout);
        });

        assertTrue(Checkout.VALIDATION_MESSAGE_DISCOUNT_PERCENT.contentEquals(exception.getMessage()));
        LOGGER.info("Invalid input data: " + exception.getMessage());

        // Printing out exception to standard output to verify test case
        System.out.println("Invalid input data: " + exception.getMessage());
    }

    @Test
    void checkoutTest2() throws DataValidationException {
        ToolMO toolMO = new ToolMO(TOOL_CODE_LADW, TOOL_TYPE_LADW, TOOL_BRAND_LADW);

        when(toolService.getToolByCode(TOOL_CODE_LADW)).thenReturn(toolMO);

        Checkout checkout = new Checkout(toolMO.getCode(), 3, 10, LocalDate.of(2020, 7, 2));
        Agreement agreement = toolRentalService.checkout(checkout);

        assertNotNull(agreement);
        assertEquals(checkout.getToolCode(), agreement.getTool().getCode());
    }

    @Test
    void checkoutTest3() throws DataValidationException {
        ToolMO toolMO = new ToolMO(TOOL_CODE_CHNS, TOOL_TYPE_CHNS, TOOL_BRAND_CHNS);

        when(toolService.getToolByCode(TOOL_CODE_CHNS)).thenReturn(toolMO);

        Checkout checkout = new Checkout(toolMO.getCode(), 5, 25, LocalDate.of(2015, 7, 2));
        Agreement agreement = toolRentalService.checkout(checkout);

        assertNotNull(agreement);
        assertEquals(checkout.getToolCode(), agreement.getTool().getCode());
    }

    @Test
    void checkoutTest4() throws DataValidationException {
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKD, TOOL_TYPE_JAKD, TOOL_BRAND_JAKD);

        when(toolService.getToolByCode(TOOL_CODE_JAKD)).thenReturn(toolMO);

        Checkout checkout = new Checkout(toolMO.getCode(), 6, 0, LocalDate.of(2015, 9, 3));
        Agreement agreement = toolRentalService.checkout(checkout);

        assertNotNull(agreement);
        assertEquals(checkout.getToolCode(), agreement.getTool().getCode());
    }

    @Test
    void checkoutTest5() throws DataValidationException {
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKR, TOOL_TYPE_JAKR, TOOL_BRAND_JAKR);

        when(toolService.getToolByCode(TOOL_CODE_JAKR)).thenReturn(toolMO);

        Checkout checkout = new Checkout(toolMO.getCode(), 9, 0, LocalDate.of(2015, 7, 2));
        Agreement agreement = toolRentalService.checkout(checkout);

        assertNotNull(agreement);
        assertEquals(checkout.getToolCode(), agreement.getTool().getCode());
    }

    @Test
    void checkoutTest6() throws DataValidationException {
        ToolMO toolMO = new ToolMO(TOOL_CODE_JAKR, TOOL_TYPE_JAKR, TOOL_BRAND_JAKR);

        when(toolService.getToolByCode(TOOL_CODE_JAKR)).thenReturn(toolMO);

        Checkout checkout = new Checkout(toolMO.getCode(), 4, 50, LocalDate.of(2020, 7, 2));
        Agreement agreement = toolRentalService.checkout(checkout);

        assertNotNull(agreement);
        assertEquals(checkout.getToolCode(), agreement.getTool().getCode());
    }
}