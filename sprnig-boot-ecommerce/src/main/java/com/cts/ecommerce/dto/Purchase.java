package com.cts.ecommerce.dto;

import java.util.Set;

import com.cts.ecommerce.entity.Address;
import com.cts.ecommerce.entity.Customer;
import com.cts.ecommerce.entity.Order;
import com.cts.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
