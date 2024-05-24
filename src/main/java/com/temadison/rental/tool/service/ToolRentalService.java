package com.temadison.rental.tool.service;

import com.temadison.rental.tool.data.Agreement;
import com.temadison.rental.tool.data.Checkout;
import com.temadison.rental.tool.data.DataValidationException;
import com.temadison.rental.tool.data.model.ToolMO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolRentalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolRentalService.class);

    @Autowired
    private ToolService toolService;

    public Agreement checkout(Checkout checkout) throws DataValidationException {
        LOGGER.info("Performing checkout from DTO: " + checkout);

        checkout.validate();

        ToolMO toolMO = toolService.getToolByCode(checkout.getToolCode());
        Agreement agreement = new Agreement(toolMO, checkout.getNumberOfDays(), checkout.getDiscountPercent(), checkout.getCheckoutDate());
        System.out.println(agreement.asReport());

        return agreement;
    }
}
