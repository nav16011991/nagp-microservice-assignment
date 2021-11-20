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
public class AddProviderUtilityCommand {

    @TargetAggregateIdentifier
    private final String providerId;

    private final String utilityId;

}