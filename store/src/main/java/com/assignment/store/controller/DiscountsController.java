package com.assignment.store.controller;

import com.assignment.store.composite.ProductsCompositeService;
import com.assignment.store.dto.product.DiscountDTO;
import com.assignment.store.dto.product.ProductDTO;
import com.assignment.store.util.enums.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {
    @Autowired
    private ProductsCompositeService productsCompositeService;

    @Secured("PROMO_GUY")
    @PostMapping
    public Boolean applyDiscountToProduct(@RequestBody DiscountDTO appliedDiscount) {
        productsCompositeService.applyDiscount(appliedDiscount);
        return true;
    }

}
