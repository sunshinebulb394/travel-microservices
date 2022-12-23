package com.travel.app.bookings.service.accountservice;

import com.travel.app.bookings.dto.AccountDto;
import com.travel.app.bookings.model.account.Account;


import java.util.List;

public interface AccountService {
    Account createAccount(AccountDto account);
    Account findByUsername(String username);
    List<Account> getAccounts();
}
