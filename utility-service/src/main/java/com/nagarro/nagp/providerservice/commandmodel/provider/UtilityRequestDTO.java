package com.nagarro.nagp.providerservice.commandmodel.provider;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class UtilityRequestDTO {
    private String utilitytId;

    private String title;

    private String category;

    private String description;
}
