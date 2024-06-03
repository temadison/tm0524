package com.temadison.rental.tool.controller;

import com.temadison.rental.tool.data.*;
import com.temadison.rental.tool.data.model.ToolMO;
import com.temadison.rental.tool.service.ToolRentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ToolRentalControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolRentalControllerTest.class);

    private static final String TOOL_CODE_LADW = "LADW";
    private static final ToolType TOOL_TYPE_LADW = ToolType.LADDER;
    private static final Brand TOOL_BRAND_LADW = Brand.WERNER;
    private static final Integer CHECKOUT_NUMBER_OF_DAYS = 5;
    private static final Integer CHECKOUT_DISCOUNT_PERCENT = 10;
    private static final LocalDate CHECKOUT_DATE = LocalDate.of(2024, 5, 20);

    @Mock
    private ToolRentalService toolRentalService;

    @InjectMocks
    private ToolRentalController toolRentalController;

    @BeforeEach
    void setUp() {
    }

    public ToolRentalControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void search() throws DataValidationException {
        ToolMO toolMO = new ToolMO(TOOL_CODE_LADW, TOOL_TYPE_LADW, TOOL_BRAND_LADW);
        Checkout checkout = new Checkout(toolMO.getCode(), CHECKOUT_NUMBER_OF_DAYS, CHECKOUT_DISCOUNT_PERCENT, CHECKOUT_DATE);
        Agreement serviceAgreement = new Agreement(toolMO, checkout.getNumberOfDays(), checkout.getDiscountPercent(), checkout.getCheckoutDate());

        when(toolRentalService.checkout(checkout)).thenReturn(serviceAgreement);

        Agreement agreement = toolRentalController.checkout(checkout);

        assertNotNull(agreement);
        assertEquals(serviceAgreement.asReport(), agreement.asReport());
    }
}