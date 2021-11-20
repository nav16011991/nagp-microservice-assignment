package com.nagarro.nagp.core.eventlib.commands;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CreateProviderCommand {

    @TargetAggregateIdentifier
    private final String providerId;

    private final String title;

}