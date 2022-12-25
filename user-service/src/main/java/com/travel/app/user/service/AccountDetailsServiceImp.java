package com.travel.app.user.service;

import com.travel.app.user.model.Account;
import com.travel.app.user.model.SecurityUser;
import com.travel.app.user.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;
@Service
public class AccountDetailsServiceImp implements UserDetailsService {
    private final AccountRepository accountRepository;

    public AccountDetailsServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return accountRepository.findByUsername(username)
               .map(SecurityUser::new)
               .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
    }

}