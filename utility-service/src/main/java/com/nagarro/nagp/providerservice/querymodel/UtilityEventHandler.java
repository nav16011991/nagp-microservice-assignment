package com.nagarro.nagp.providerservice.querymodel;

import com.nagarro.nagp.core.eventlib.events.UtilityCreatedEvent;
import com.nagarro.nagp.core.eventlib.events.UtilityUpdatedEvent;
import com.nagarro.nagp.providerservice.coreapi.queries.FindAllUtilityQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.FindUtilityByCategoryQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.FindUtilityByIdQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.Utility;
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
public class UtilityEventHandler {

    private final Map<String, Utility> utilities = new HashMap<>();

    @EventHandler
    public void on(UtilityCreatedEvent event) {
        utilities.put(event.getUtilitytId(), new Utility(event.getUtilitytId(), event.getTitle(), event.getCategory(), event.getDescription()));
    }

    @EventHandler
    public void on(UtilityUpdatedEvent event) {
        utilities.computeIfPresent(event.getUtilitytId(), (utilityId, utility) -> {
            utility.setCategory(event.getCategory());
            utility.setDescription(event.getDescription());
            return utility;
        });
    }


    @QueryHandler
    public List<Utility> handle(FindAllUtilityQuery query) {
        return new ArrayList<>(utilities.values());
    }

    @QueryHandler
    public List<Utility> handle(FindUtilityByIdQuery query) {
        return new ArrayList<>(utilities.values().stream().filter(e->query.getUtilityId().equals(e.getUtilitytId())).collect(Collectors.toList()));
    }

    @QueryHandler
    public List<Utility> handle(FindUtilityByCategoryQuery query) {
        return new ArrayList<>(utilities.values().stream().filter(e->query.getCategory().equals(e.getCategory())).collect(Collectors.toList()));
    }
}
