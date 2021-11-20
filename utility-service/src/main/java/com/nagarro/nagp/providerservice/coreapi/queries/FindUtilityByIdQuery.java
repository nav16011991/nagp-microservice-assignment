package com.nagarro.nagp.providerservice.coreapi.queries;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class FindUtilityByIdQuery {
    private final String utilityId;
}
