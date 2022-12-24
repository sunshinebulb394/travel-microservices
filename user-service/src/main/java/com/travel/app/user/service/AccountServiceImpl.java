package com.travel.app.user.service;

import com.travel.app.user.model.Account;
import com.travel.app.user.model.RoleName;
import com.travel.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountServiceInt {

    private final UserRepository userRepository;
    @Override
    public Account findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Account save(Account account) {
        account.setRoles(Set.of(RoleName.ROLE_USER));
        return userRepository.save(account);
    }

    @Override
    public void delete(Account account) {
        userRepository.delete(account);
    }


}

