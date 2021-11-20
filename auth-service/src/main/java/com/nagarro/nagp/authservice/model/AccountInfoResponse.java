package com.nagarro.nagp.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfoResponse {
    private Long id;

    private String accountId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String userRole;
}
