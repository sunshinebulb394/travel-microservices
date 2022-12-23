package com.travel.app.bookings.rolecreation;


import com.travel.app.bookings.model.account.Role;
import com.travel.app.bookings.model.account.RoleName;
import com.travel.app.bookings.repository.accountrepository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String ...args) throws Exception {
        Role roleUser = new Role(1L, "123", RoleName.ROLE_USER);
        Role roleAdmin = new Role(2L, "456", RoleName.ROLE_ADMIN);
        roleRepository.saveAll(asList(roleUser, roleAdmin));
    }
}
