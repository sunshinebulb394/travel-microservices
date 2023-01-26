package com.travel.app.bus.controller;

import com.travel.app.bus.pojo.users.Account;
import com.travel.app.bus.service.AccountServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountServiceImp accountService;

    @PostMapping("/create-user")
    public ResponseEntity<Account> createUserAccount (@RequestBody Account account) {
        Account newAccount = accountService.createUserAccount(account);
        return ResponseEntity.ok(newAccount);
    }



    @PostMapping("/create-admin")
    public ResponseEntity<Account> createAdminAccount (@RequestBody Account account) {
        Account newAccount = accountService.createAdminAccount(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Account>>getAllAccount() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) throws AccountNotFoundException {
        return ResponseEntity.ok(accountService.updateAccount(id, account));
    }

}
