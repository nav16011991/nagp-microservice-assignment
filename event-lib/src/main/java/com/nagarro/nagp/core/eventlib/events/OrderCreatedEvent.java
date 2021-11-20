package com.nagarro.nagp.core.eventlib.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class OrderCreatedEvent {

    private final String orderId;

    private final String userId;

    private final String utilityId;

    private final String location;
}