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
public class CreateUtilityCommand {

    @TargetAggregateIdentifier
    private final String utilitytId;

    private final String title;

    private final String category;

    private final String description;

}