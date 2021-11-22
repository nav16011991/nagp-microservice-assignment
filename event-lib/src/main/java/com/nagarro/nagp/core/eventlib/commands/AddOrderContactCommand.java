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
public class AddOrderContactCommand {

    @TargetAggregateIdentifier
    private final String orderId;

    private String addressLine;

    private String city;

    private String state;

    private String pinCode;

    private String contactNumber;
}