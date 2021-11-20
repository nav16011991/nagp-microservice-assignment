package com.nagarro.nagp.providerservice.coreapi.queries;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class FindUtilityByCategoryQuery {
    private final String category;
}
