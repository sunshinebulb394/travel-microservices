package com.travel.app.user.service;

import com.travel.app.user.dto.AccountDto;
import com.travel.app.user.model.Account;
import com.travel.app.user.model.RoleName;
import com.travel.app.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.travel.app.user.model.RoleName.ROLE_SUPER_ADMIN;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountServiceInt,CommandLineRunner {

    private final AccountRepository accountRepository;

    private final PasswordEncoder encoder;
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username).get();
    }

    @Override
    public Account saveUser(AccountDto accountDto) {
        Account account = new Account();
        account.setPhoneNumber(accountDto.getPhoneNumber());
        account.setUsername(accountDto.getUsername());
        account.setPassword(encoder.encode(accountDto.getPassword()));
        account.setRoles(Set.of(RoleName.ROLE_USER));
        return accountRepository.save(account);
    }
    @Override
    public Account saveAdmin(AccountDto accountDto) {
        Account account = new Account();
        account.setPhoneNumber(accountDto.getPhoneNumber());
        account.setUsername(accountDto.getUsername());
        account.setPassword(encoder.encode(accountDto.getPassword()));
        account.setRoles(Set.of(RoleName.ROLE_ADMIN));
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(AccountDto accountDto) {
        return accountRepository.findByUsername(accountDto.getUsername()).map(a -> {
            a.setPhoneNumber(accountDto.getPhoneNumber());
            a.setUsername(accountDto.getUsername());
            a.setPassword(encoder.encode(accountDto.getPassword()));
            return accountRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public String deleteUser(Long id) {
        // Get the authenticated user
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        // Get the account for the authenticated user
        Account loggedInAccount = accountRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Account not found"));
        Account accountToBeDeleted = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        // Check if the authenticated user has the ROLE_SUPER_ADMIN role

        if (accountToBeDeleted.getRoles().contains(RoleName.ROLE_ADMIN) && loggedInAccount.getRoles().contains(RoleName.ROLE_ADMIN)) {
            throw new RuntimeException("Only ROLE_SUPER_ADMIN can delete an admin");

        }
        // Delete the admin
        accountRepository.deleteById(id);
        return "User deleted";
    }
    @Override
    public String deleteAdmin(Long id) {
        accountRepository.deleteById(id);
        return "Admin deleted";
    }

    @Override
    public List<Account> getAllUserAccounts() {
        return accountRepository.findAll()
                .stream()
                .filter(role -> role.getRoles().contains(RoleName.ROLE_USER))
                .toList();

    }

    @Override
    public List<Account> getAllAdminAccounts() {
        return accountRepository.findAll()
                .stream()
                .filter(role -> role.getRoles().contains(RoleName.ROLE_ADMIN))
                .toList();
    }


    @Override
    public void run(String... args) throws Exception {
        Account superAdmin = new Account(1L,"george", encoder.encode("Wonderful143"),null, Set.of(ROLE_SUPER_ADMIN));
        Account account1 = new Account(2L,"admin", encoder.encode("admin"),"123", Set.of(RoleName.ROLE_ADMIN));
        Account account2 = new Account(3L,"user", encoder.encode("user"),"456", Set.of(RoleName.ROLE_USER));

        accountRepository.saveAll(List.of(superAdmin,account1, account2));
    }
}



