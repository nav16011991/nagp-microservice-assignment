package com.nagarro.nagp.providerservice.commandmodel.provider;

import com.nagarro.nagp.core.eventlib.commands.AddProviderLocationCommand;
import com.nagarro.nagp.core.eventlib.commands.AddProviderUtilityCommand;
import com.nagarro.nagp.core.eventlib.commands.CreateProviderCommand;
import com.nagarro.nagp.core.eventlib.events.ProviderCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.ProviderLocationAddedEvent;
import com.nagarro.nagp.core.eventlib.events.ProviderUtilityAddedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class ProviderAggregate {

    @AggregateIdentifier
    private String providerId;

    private String title;

    private List<String> locations;

    private List<String> utilities;

    protected ProviderAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }

    @CommandHandler
    public ProviderAggregate(CreateProviderCommand command) {
        apply(new ProviderCreatedEvent(command.getProviderId(), command.getTitle()));
    }

    @EventSourcingHandler
    public void on(ProviderCreatedEvent event) {
        this.providerId = event.getProvidertId();
        this.title = event.getTitle();
        this.locations = new ArrayList<>();
        this.utilities = new ArrayList<>();
    }

    @CommandHandler
    public ProviderAggregate(AddProviderLocationCommand command) {
        apply(new ProviderCreatedEvent(command.getProviderId(), command.getLocation()));
    }

    @EventSourcingHandler
    public void on(ProviderLocationAddedEvent event) {
        this.locations.add(event.getLocation());
    }

    @CommandHandler
    public ProviderAggregate(AddProviderUtilityCommand command) {
        apply(new ProviderCreatedEvent(command.getProviderId(), command.getUtilityId()));
    }

    @EventSourcingHandler
    public void on(ProviderUtilityAddedEvent event) {
        this.utilities.add(event.getUtilityId());
    }

}
