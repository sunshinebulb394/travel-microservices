package com.travel.app.user.controller;

import com.travel.app.user.model.Account;
import com.travel.app.user.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountServiceImpl accountService;

    @PostMapping("/users/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.save(account);
    }

    @DeleteMapping("/admin/delete")
    public void deleteAccount(@RequestBody Account account) {
        accountService.delete(account);
    }

}
