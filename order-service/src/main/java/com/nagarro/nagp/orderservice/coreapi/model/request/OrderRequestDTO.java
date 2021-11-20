package com.nagarro.nagp.orderservice.coreapi.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderRequestDTO {

    private String utilityId;

    private String location;
}
