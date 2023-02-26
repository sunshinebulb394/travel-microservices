package com.travel.app.bus.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String driversLicense;

    @NonNull
    private String name;

    private String contact;


}
