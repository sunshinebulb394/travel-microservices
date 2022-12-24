package com.travel.app.user.service;

import com.travel.app.user.model.Account;
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
@RequiredArgsConstructor
public class AccountDetailsServiceImp implements UserDetailsService {
    private final AccountServiceInt userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userService.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
        if(account.getRoles() == null || account.getRoles().isEmpty()) {
            throw new RuntimeException("User has no roles");
        }
        Collection<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name())).collect(toList());

        return new User(account.getUsername(), account.getPassword(), authorities);
    }

}