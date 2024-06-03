package com.temadison.rental.tool.controller;

import com.temadison.rental.tool.data.Agreement;
import com.temadison.rental.tool.data.Checkout;
import com.temadison.rental.tool.data.DataValidationException;
import com.temadison.rental.tool.service.ToolRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ToolRentalController {

    @Autowired
    private ToolRentalService toolRentalService;

    @RequestMapping(value = "/checkout",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Agreement checkout(@RequestBody Checkout checkout) {
        try {
            return toolRentalService.checkout(checkout);
        } catch (DataValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
