package com.nagarro.nagp.providerservice.gui;

import com.nagarro.nagp.core.eventlib.commands.CreateUtilityCommand;
import com.nagarro.nagp.providerservice.commandmodel.provider.ProviderRequestDTO;
import com.nagarro.nagp.providerservice.coreapi.queries.FindAllProviderQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.FindProviderByIdQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.Provider;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class ProviderRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public ProviderRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public CompletableFuture<String> createUtility(@RequestBody ProviderRequestDTO providerRequestDTO) {
        return createUtility(UUID.randomUUID().toString(), providerRequestDTO);
    }

    @PostMapping("/{provider-id}")
    public CompletableFuture<String> createUtility(@PathVariable("provider-id") String providerId, @RequestBody ProviderRequestDTO providerRequestDTO) {
        return commandGateway.send(new CreateUtilityCommand(providerId, providerRequestDTO.getTitle(), providerRequestDTO.getCategory(), providerRequestDTO.getDescription()));
    }

    @GetMapping("/all")
    public CompletableFuture<List<Provider>> findAllUtilities() {
        return queryGateway.query(new FindAllProviderQuery(), ResponseTypes.multipleInstancesOf(Provider.class));
    }

    @GetMapping("/{provider-id}")
    public CompletableFuture<List<Provider>> findAllUtilities(@PathVariable("provider-id") String providerId) {
        return queryGateway.query(new FindProviderByIdQuery(providerId), ResponseTypes.multipleInstancesOf(Provider.class));
    }
}
