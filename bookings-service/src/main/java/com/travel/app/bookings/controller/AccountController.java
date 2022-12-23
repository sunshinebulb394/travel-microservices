package com.travel.app.bookings.controller;

import com.travel.app.bookings.dto.AccountDto;
import com.travel.app.bookings.model.account.Account;
import com.travel.app.bookings.service.accountservice.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount (@RequestBody AccountDto account) {
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.created(getLocation(newAccount.getId().intValue())).body(newAccount);
    }

    protected static URI getLocation(Integer id) {
        return fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }

    @GetMapping
    public ResponseEntity<List<Account>>getAccount() {
        return ResponseEntity.ok(accountService.getAccounts());
    }
}
