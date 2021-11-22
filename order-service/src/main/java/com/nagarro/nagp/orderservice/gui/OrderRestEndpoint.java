package com.nagarro.nagp.orderservice.gui;

import com.nagarro.nagp.core.eventlib.commands.AddOrderContactCommand;
import com.nagarro.nagp.core.eventlib.commands.ConfirmOrderCommand;
import com.nagarro.nagp.core.eventlib.commands.CreateOrderCommand;
import com.nagarro.nagp.orderservice.coreapi.model.request.OrderContactDTO;
import com.nagarro.nagp.orderservice.coreapi.model.request.OrderRequestDTO;
import com.nagarro.nagp.orderservice.coreapi.queries.FindAllOrderQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.FindOrderByLocationQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.FindOrderByUserQuery;
import com.nagarro.nagp.orderservice.coreapi.queries.Order;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class OrderRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public OrderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestHeader("user-id") String userId, @RequestBody OrderRequestDTO orderRequestDTO) {
        return createOrder(UUID.randomUUID().toString(), userId, orderRequestDTO);
    }

    @PostMapping("/{order-id}")
    public CompletableFuture<String> createOrder(@PathVariable("order-id") String orderId,@RequestHeader("user-id") String userId, @RequestBody OrderRequestDTO orderRequestDTO) {
        return commandGateway.send(new CreateOrderCommand(orderId, userId, orderRequestDTO.getUtilityId(), orderRequestDTO.getLocation()));
    }

    @PostMapping("/{order-id}/contact")
    public CompletableFuture<String> createOrder(@PathVariable("order-id") String orderId, @RequestBody OrderContactDTO orderContactDTO) {
        return commandGateway.send(new AddOrderContactCommand(orderId,
                orderContactDTO.getAddressLine(),
                orderContactDTO.getCity(),
                orderContactDTO.getState(),
                orderContactDTO.getPinCode(),
                orderContactDTO.getContactNumber()));
    }

    @PostMapping("/{order-id}/confirm")
    public CompletableFuture<Void> confirmOrder(@PathVariable("order-id") String orderId) {
        return commandGateway.send(new ConfirmOrderCommand(orderId));
    }

    @GetMapping("/all")
    public CompletableFuture<List<Order>> findAllOrders() {
        return queryGateway.query(new FindAllOrderQuery(), ResponseTypes.multipleInstancesOf(Order.class));
    }

    @GetMapping("/user")
    public CompletableFuture<List<Order>> findAllOrdersByUser(@RequestHeader("user-id") String userId) {
        return queryGateway.query(new FindOrderByUserQuery(userId), ResponseTypes.multipleInstancesOf(Order.class));
    }

    @GetMapping("/location/{location}")
    public CompletableFuture<List<Order>> findAllOrdersByLocation(@PathVariable("location") String location) {
        return queryGateway.query(new FindOrderByLocationQuery(location), ResponseTypes.multipleInstancesOf(Order.class));
    }


    public CompletableFuture<List<Order>> findAllOrders(@RequestHeader("user-id") String userId) {
        return queryGateway.query(new FindOrderByUserQuery(userId), ResponseTypes.multipleInstancesOf(Order.class));
    }
}
