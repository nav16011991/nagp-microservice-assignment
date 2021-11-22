package com.nagarro.nagp.orderservice.coreapi.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderContactDTO {

    private String addressLine;

    private String city;

    private String state;

    private String pinCode;

    private String contactNumber;
}
