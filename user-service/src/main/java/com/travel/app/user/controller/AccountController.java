package com.travel.app.user.controller;

import com.travel.app.user.dto.AccountDto;
import com.travel.app.user.model.Account;
import com.travel.app.user.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    private final AccountServiceImpl accountService;

    @PostMapping("/user/create")
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.saveUser(accountDto));
    }


    @GetMapping
    public ResponseEntity<String> getAccount(Principal principal) {
        return ResponseEntity.ok("Hello  " + principal.getName());
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')  or hasRole('ROLE_ADMIN') ")
    @GetMapping("/admin/user-accounts")
    public ResponseEntity<List<Account>> getAllUserAccounts(){
        return ResponseEntity.ok(accountService.getAllUserAccounts());
    }


    @PostMapping("/admin/create")
    public ResponseEntity<Account> createAdminAccount(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(accountService.saveAdmin(accountDto));
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("super-admin/admin-accounts")
    public ResponseEntity<List<Account>> getAllAdminAccounts(){
        return ResponseEntity.ok(accountService.getAllAdminAccounts());
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPER_ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<Account> findAccount(@PathVariable String username) {
        return ResponseEntity.ok(accountService.findByUsername(username));
    }


    @PutMapping("/update-account")
    public ResponseEntity<Account> updateAccount(@RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.updateAccount(accountDto));
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String> deleteUserAccount(@PathVariable("id") Long id) {
       return ResponseEntity.ok(accountService.deleteUser(id));
    }

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN') ")
    @DeleteMapping("/super-admin/delete/{id}")
    public ResponseEntity<String> deleteAdminAccount(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountService.deleteAdmin(id));
    }


}
