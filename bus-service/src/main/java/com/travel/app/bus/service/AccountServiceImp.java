package com.travel.app.bus.service;

import com.travel.app.bus.pojo.users.Account;
import com.travel.app.bus.pojo.users.Role;
import com.travel.app.bus.repository.AccountRepository;
import com.travel.app.bus.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountServiceInt {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public Account createUserAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);
        return accountRepository.save(account);
    }

    @Override
    public Account createAdminAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles);
        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account updateAccount(Long id, Account account) throws AccountNotFoundException {
    return  accountRepository.findById(id).map(account1 -> {
        if(account.getUsername() != null) {
            throw new RuntimeException("Username cannot be updated");
        }
        account1.setPassword(encoder.encode(account.getPassword()));
        return accountRepository.save(account1);
        }).orElseThrow(() -> new AccountNotFoundException("Account not found"));


    }
}
