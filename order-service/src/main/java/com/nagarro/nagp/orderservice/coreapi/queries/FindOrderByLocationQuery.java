package com.nagarro.nagp.orderservice.coreapi.queries;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FindOrderByLocationQuery {
    private final String location;

}
