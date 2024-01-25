package com.assignment.store.controller;

import com.assignment.store.composite.ProductsCompositeService;
import com.assignment.store.dto.product.DiscountDTO;
import com.assignment.store.util.exception.InvalidDiscountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts")
public class DiscountsController {
    @Autowired
    private ProductsCompositeService productsCompositeService;

    //@Secured("PROMO_GUY")
    @PostMapping
    public ResponseEntity<Boolean> applyDiscountToProduct(@RequestBody DiscountDTO appliedDiscount) throws InvalidDiscountException {
        productsCompositeService.applyDiscount(appliedDiscount);
        return ResponseEntity.ok(true);
    }

}
