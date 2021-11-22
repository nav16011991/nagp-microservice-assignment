package com.nagarro.nagp.orderservice.commandmodel.order;

import com.nagarro.nagp.core.eventlib.commands.AddOrderContactCommand;
import com.nagarro.nagp.core.eventlib.commands.ConfirmOrderCommand;
import com.nagarro.nagp.core.eventlib.commands.CreateOrderCommand;
import com.nagarro.nagp.core.eventlib.events.*;
import com.nagarro.nagp.orderservice.coreapi.exceptions.OrderAlreadyConfirmedException;
import com.nagarro.nagp.orderservice.coreapi.queries.ContactInformation;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String utilityId;
    private String userId;
    private String location;
    private ContactInformation contactInformation;
    private boolean orderConfirmed;


    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.getOrderId(), command.getUserId(), command.getUtilityId(), command.getLocation()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        if (orderConfirmed) {
            return;
        }

        apply(new OrderComfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(AddOrderContactCommand command) {
        if (orderConfirmed) {
            throw new OrderAlreadyConfirmedException(command.getOrderId());
        }
        apply(new OrderContactAddedEvent(orderId, command.getAddressLine(), command.getCity(), command.getState(), command.getPinCode(), command.getContactNumber()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.getOrderId();
        this.utilityId = event.getUtilityId();
        this.userId = event.getUserId();
        this.location = event.getLocation();
        this.contactInformation = null;
        this.orderConfirmed = false;
    }

    @EventSourcingHandler
    public void on(OrderComfirmedEvent event) {
        this.orderConfirmed = true;
    }

    @EventSourcingHandler
    public void on(OrderContactAddedEvent event) {
        this.contactInformation = ContactInformation.builder()
                .addressLine(event.getAddressLine())
                .city(event.getCity())
                .state(event.getState())
                .pinCode(event.getPinCode())
                .contactNumber(event.getContactNumber())
                .build();
    }


    protected OrderAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }
}