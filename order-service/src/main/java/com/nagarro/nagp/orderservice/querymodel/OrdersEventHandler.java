package com.nagarro.nagp.orderservice.querymodel;

import com.nagarro.nagp.core.eventlib.events.OrderComfirmedEvent;
import com.nagarro.nagp.core.eventlib.events.OrderCreatedEvent;
import com.nagarro.nagp.orderservice.coreapi.queries.FindAllOrderQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.FindOrderByLocationQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.FindOrderByUserQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.Order;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ProcessingGroup("orders")
public class OrdersEventHandler {

    private final Map<String, Order> orders = new HashMap<>();

    @EventHandler
    public void on(OrderCreatedEvent event) {

        orders.put(event.getOrderId(), new Order(event.getOrderId(), event.getUserId(), event.getUtilityId(), event.getLocation()));
    }

    @EventHandler
    public void on(OrderComfirmedEvent event) {
        orders.computeIfPresent(event.getOrderId(), (orderId, order) -> {
            order.setOrderConfirmed();
            return order;
        });
    }


    @QueryHandler
    public List<Order> handle(FindAllOrderQuery query) {
        return new ArrayList<>(orders.values());
    }

    @QueryHandler
    public List<Order> handle(FindOrderByUserQuery query) {
        return new ArrayList<>(orders.values().stream().filter(e->query.getUserId().equals(e.getUserId())).collect(Collectors.toList()));
    }

    @QueryHandler
    public List<Order> handle(FindOrderByLocationQuery query) {
        return new ArrayList<>(orders.values().stream().filter(e->query.getLocation().equals(e.getLocation())).collect(Collectors.toList()));
    }
}