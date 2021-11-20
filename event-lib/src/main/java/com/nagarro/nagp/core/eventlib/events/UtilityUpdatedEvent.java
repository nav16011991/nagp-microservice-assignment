package com.nagarro.nagp.core.eventlib.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class UtilityUpdatedEvent {

    private final String utilitytId;

    private final String category;

    private final String description;
}