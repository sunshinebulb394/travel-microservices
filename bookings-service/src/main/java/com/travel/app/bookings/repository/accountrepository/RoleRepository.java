package com.travel.app.bookings.repository.accountrepository;

import com.travel.app.bookings.model.account.Role;
import com.travel.app.bookings.model.account.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}