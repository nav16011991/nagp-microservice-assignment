package com.nagarro.nagp.providerservice.coreapi.queries;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Provider {

    private String providertId;

    private String title;

    private List<String> locations;

    private List<String> utilities;
}
