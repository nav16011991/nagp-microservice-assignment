package com.nagarro.nagp.accountservice.model.response;

import com.nagarro.nagp.accountservice.model.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDTO {

    private Long id;

    private String accountId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private UserRole userRole;
}
