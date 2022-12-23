package com.travel.app.bookings.service.accountservice;


import com.travel.app.bookings.dto.AccountDto;
import com.travel.app.bookings.model.account.Account;
import com.travel.app.bookings.model.account.Role;
import com.travel.app.bookings.model.account.RoleName;
import com.travel.app.bookings.repository.accountrepository.AccountRepository;
import com.travel.app.bookings.repository.accountrepository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public Account createAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(encoder.encode(accountDto.getPassword()));
        Role role = roleRepository.findByName(RoleName.ROLE_USER);
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
}
