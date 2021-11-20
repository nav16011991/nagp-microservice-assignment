package com.nagarro.nagp.providerservice.gui;

import com.nagarro.nagp.core.eventlib.commands.CreateUtilityCommand;
import com.nagarro.nagp.providerservice.commandmodel.provider.UtilityRequestDTO;
import com.nagarro.nagp.providerservice.coreapi.queries.FindAllUtilityQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.FindUtilityByCategoryQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.FindUtilityByIdQuery;
import com.nagarro.nagp.providerservice.coreapi.queries.Utility;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class UtilityRestEndpoint {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public UtilityRestEndpoint(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public CompletableFuture<String> createUtility(@RequestBody UtilityRequestDTO utilityRequestDTO) {
        return createUtility(UUID.randomUUID().toString(), utilityRequestDTO);
    }

    @PostMapping("/{utility-id}")
    public CompletableFuture<String> createUtility(@PathVariable("utility-id") String utilityId, @RequestBody UtilityRequestDTO utilityRequestDTO) {
        return commandGateway.send(new CreateUtilityCommand(utilityId, utilityRequestDTO.getTitle(), utilityRequestDTO.getCategory(), utilityRequestDTO.getDescription()));
    }

    @GetMapping("/all")
    public CompletableFuture<List<Utility>> findAllUtilities() {
        return queryGateway.query(new FindAllUtilityQuery(), ResponseTypes.multipleInstancesOf(Utility.class));
    }

    @GetMapping("/{utility-id}")
    public CompletableFuture<List<Utility>> findAllUtilities(@PathVariable("utility-id") String utilityId) {
        return queryGateway.query(new FindUtilityByIdQuery(utilityId), ResponseTypes.multipleInstancesOf(Utility.class));
    }

    @GetMapping("/category/{category}")
    public CompletableFuture<List<Utility>> findAllUtilitiesByCategory(@PathVariable("category") String category) {
        return queryGateway.query(new FindUtilityByCategoryQuery(category), ResponseTypes.multipleInstancesOf(Utility.class));
    }
}
