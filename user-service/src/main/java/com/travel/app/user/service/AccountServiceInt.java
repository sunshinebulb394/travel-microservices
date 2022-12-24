package com.travel.app.user.service;

import com.travel.app.user.model.Account;

public interface AccountServiceInt {
    Account findByUsername(String username);

    Account save(Account account);

    void delete(Account account);

}
