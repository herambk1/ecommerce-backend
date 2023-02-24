package com.cts.ecommerce.service;

import com.cts.ecommerce.dto.Purchase;
import com.cts.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

	PurchaseResponse placeOrder(Purchase purchase);

}
