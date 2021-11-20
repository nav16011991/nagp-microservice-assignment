package com.nagarro.nagp.accountservice.controller;

import com.nagarro.nagp.accountservice.model.request.AccountRequestDTO;
import com.nagarro.nagp.accountservice.model.response.AccountResponseDTO;
import com.nagarro.nagp.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountResponseDTO> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public AccountResponseDTO get(@PathVariable("id") Long id) {
        return accountService.get(id);
    }

    @GetMapping("/user/{username}")
    public AccountResponseDTO getbyUsername(@PathVariable("username") String username) {
        return accountService.getByUsername(username);
    }

    @PostMapping
    public void save(@RequestBody AccountRequestDTO accountRequestDTO) {
        accountService.save(accountRequestDTO);
    }

    @PutMapping
    @RequestMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody AccountRequestDTO accountRequestDTO) {
        accountRequestDTO.setId(id);
        accountService.update(accountRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        accountService.delete(id);
    }

}
