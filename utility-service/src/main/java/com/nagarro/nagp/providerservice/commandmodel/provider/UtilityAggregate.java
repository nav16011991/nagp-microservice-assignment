package com.nagarro.nagp.providerservice.commandmodel.provider;

import com.nagarro.nagp.core.eventlib.commands.CreateUtilityCommand;
import com.nagarro.nagp.core.eventlib.commands.UpdateUtilityCommand;
import com.nagarro.nagp.core.eventlib.events.UtilityCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.UtilityUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class UtilityAggregate {

    @AggregateIdentifier
    private String utilityId;

    private String title;

    private String category;

    private String description;

    protected UtilityAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    @CommandHandler
    public UtilityAggregate(CreateUtilityCommand command) {
        apply(new UtilityCreatedEvent(command.getUtilitytId(), command.getTitle(), command.getCategory(), command.getDescription()));
    }

    @EventSourcingHandler
    public void on(UtilityCreatedEvent event) {
        this.utilityId = event.getUtilitytId();
        this.title = event.getTitle();
        this.category = event.getCategory();
        this.description = event.getDescription();
    }

    @CommandHandler
    public UtilityAggregate(UpdateUtilityCommand command) {
        apply(new UtilityUpdatedEvent(command.getUtilitytId(), command.getCategory(), command.getDescription()));
    }

    @EventSourcingHandler
    public void on(UtilityUpdatedEvent event) {
        this.utilityId = event.getUtilitytId();
        this.category = event.getCategory();
        this.description = event.getDescription();
    }

}
