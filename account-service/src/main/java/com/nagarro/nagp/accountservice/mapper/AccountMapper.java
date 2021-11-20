package com.nagarro.nagp.accountservice.mapper;

import com.nagarro.nagp.accountservice.model.request.AccountRequestDTO;
import com.nagarro.nagp.accountservice.model.response.AccountResponseDTO;
import com.nagarro.nagp.accountservice.persistence.entity.AccountEntity;
import org.springframework.util.ObjectUtils;

public class AccountMapper {

    public static AccountResponseDTO toDTO(AccountEntity accountEntity) {
            if(ObjectUtils.isEmpty(accountEntity)) {
                return null;
            }
            return AccountResponseDTO.builder()
                    .id(accountEntity.getId())
                    .accountId(accountEntity.getAccountId())
                    .firstName(accountEntity.getFirstName())
                    .lastName(accountEntity.getLastName())
                    .username(accountEntity.getUsername())
                    .password(accountEntity.getPassword())
                    .userRole(accountEntity.getUserRole())
                    .build();
    }

    public static AccountEntity toEntity(AccountRequestDTO accountRequestDTO) {
        return AccountEntity.builder()
                .id(accountRequestDTO.getId())
                .accountId(accountRequestDTO.getAccountId())
                .firstName(accountRequestDTO.getFirstName())
                .lastName(accountRequestDTO.getLastName())
                .username(accountRequestDTO.getUsername())
                .password(accountRequestDTO.getPassword())
                .userRole(accountRequestDTO.getUserRole())
                .build();
    }
}
