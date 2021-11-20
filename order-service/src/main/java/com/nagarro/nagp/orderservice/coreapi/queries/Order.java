package com.nagarro.nagp.orderservice.coreapi.queries;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public class Order {

    private final String orderId;
    private final String userId;
    private final String utilityId;
    private final String location;
    private OrderStatus orderStatus;

    public Order(String orderId, String userId, String utilityId, String location) {
        this.orderId = orderId;
        this.userId = userId;
        this.utilityId = utilityId;
        this.location = location;
        orderStatus = OrderStatus.CREATED;
    }

    public void setOrderConfirmed() {
        orderStatus = OrderStatus.CONFIRMED;
    }
}
