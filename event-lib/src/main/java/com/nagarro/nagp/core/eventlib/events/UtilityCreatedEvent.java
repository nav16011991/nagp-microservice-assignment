package com.nagarro.nagp.core.eventlib.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class UtilityCreatedEvent {

    private final String utilitytId;

    private final String title;

    private final String category;

    private final String description;
}