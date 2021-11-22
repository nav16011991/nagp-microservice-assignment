package com.nagarro.nagp.core.eventlib.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class OrderContactAddedEvent {

    private final String orderId;

    private String addressLine;

    private String city;

    private String state;

    private String pinCode;

    private String contactNumber;
}