package com.travel.app.bus.service;

import com.travel.app.bus.pojo.users.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AccountServiceInt {
    Account createUserAccount(Account account);

    Account createAdminAccount(Account account);
    Account findByUsername(String username);
    List<Account> getAccounts();
    void deleteAccount(Long id) throws AccountNotFoundException;
}
