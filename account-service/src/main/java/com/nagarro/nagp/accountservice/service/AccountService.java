package com.nagarro.nagp.accountservice.service;

import com.nagarro.nagp.accountservice.model.request.AccountRequestDTO;
import com.nagarro.nagp.accountservice.model.response.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    List<AccountResponseDTO> getAll();

    AccountResponseDTO get(Long id);

    void save(AccountRequestDTO accountRequestDTO);

    void delete(Long id);

    void update(AccountRequestDTO accountRequestDTO);

    AccountResponseDTO getByUsername(String username);
}
