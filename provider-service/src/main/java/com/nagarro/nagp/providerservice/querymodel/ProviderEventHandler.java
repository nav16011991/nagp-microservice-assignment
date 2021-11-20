package com.nagarro.nagp.providerservice.querymodel;

import com.nagarro.nagp.core.eventlib.events.ProviderCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.ProviderLocationAddedEvent;
import com.nagarro.nagp.core.eventlib.events.ProviderUtilityAddedEvent;
import com.nagarro.nagp.providerservice.coreapi.queries.FindAllProviderQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.FindProviderByIdQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.Provider;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ProcessingGroup("utilities")
public class ProviderEventHandler {

    private final Map<String, Provider> providers = new HashMap<>();

    @EventHandler
    public void on(ProviderCreatedEvent event) {
        providers.put(event.getProvidertId(), new Provider(event.getProvidertId(), event.getTitle(), new ArrayList<>(), new ArrayList<>()));
    }

    @EventHandler
    public void on(ProviderLocationAddedEvent event) {
        providers.computeIfPresent(event.getProvidertId(), (providerId, provider) -> {
            provider.getLocations().add(event.getLocation());
            return provider;
        });
    }

    @EventHandler
    public void on(ProviderUtilityAddedEvent event) {
        providers.computeIfPresent(event.getProvidertId(), (providerId, provider) -> {
            provider.getUtilities().add(event.getUtilityId());
            return provider;
        });
    }

    @QueryHandler
    public List<Provider> handle(FindAllProviderQuery query) {
        return new ArrayList<>(providers.values());
    }

    @QueryHandler
    public List<Provider> handle(FindProviderByIdQuery query) {
        return new ArrayList<>(providers.values().stream().filter(e->query.getProviderId().equals(e.getProvidertId())).collect(Collectors.toList()));
    }
}
