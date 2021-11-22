package com.nagarro.nagp.orderservice.coreapi.queries;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class ContactInformation {
    private String addressLine;

    private String city;

    private String state;

    private String pinCode;

    private String contactNumber;
}
