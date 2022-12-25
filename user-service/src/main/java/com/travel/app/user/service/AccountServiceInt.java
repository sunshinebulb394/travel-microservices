package com.travel.app.user.service;

import com.travel.app.user.dto.AccountDto;
import com.travel.app.user.model.Account;

import java.util.List;

public interface AccountServiceInt {
    Account findByUsername(String username);

    Account saveUser(AccountDto accountDto);
    Account saveAdmin(AccountDto accountDto);

    Account updateAccount(AccountDto accountDto);

    String deleteUser(Long id);
    String deleteAdmin(Long id);

    List<Account> getAllUserAccounts();

    List<Account> getAllAdminAccounts();



}
