package com.travel.app.bookings.model.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    @Enumerated(EnumType.STRING)
    private RoleName name;

    // Role - High level
    // Permissions or authorities - more specific
}
