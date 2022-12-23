package com.travel.app.bookings.dto;


import com.travel.app.bookings.model.account.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.io.Serializable;
import java.util.Set;


@Data
public class AccountDto implements Serializable {
    @NotNull
    private  String username;
    @NotNull
    private  String password;
    private Set<Role> roles;

}