package com.nagarro.nagp.core.eventlib.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ProviderCreatedEvent {

    private final String providertId;

    private final String title;
}