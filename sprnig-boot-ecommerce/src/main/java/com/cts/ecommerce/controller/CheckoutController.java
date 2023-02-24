package com.cts.ecommerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.ecommerce.dto.Purchase;
import com.cts.ecommerce.dto.PurchaseResponse;
import com.cts.ecommerce.service.CheckoutService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	 private CheckoutService checkoutService;

	    public CheckoutController(CheckoutService checkoutService) {
	        this.checkoutService = checkoutService;
	    }
	    
	    @PostMapping("/purchase")
	    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

	        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

	        return purchaseResponse;
	    }
}
