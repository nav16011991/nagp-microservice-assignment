package com.nagarro.nagp.core.eventlib.commands;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    private final String userId;

    private final String utilityId;

    private final String location;
}