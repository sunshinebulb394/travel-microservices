package com.travel.app.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
@Data
public class AccountDto {
    @Column(unique = true)
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String phoneNumber;
}
