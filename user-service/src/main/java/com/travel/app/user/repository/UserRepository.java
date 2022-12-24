package com.travel.app.user.repository;

import com.travel.app.user.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account,Long> {
    Account findByUsername(String username);
}
