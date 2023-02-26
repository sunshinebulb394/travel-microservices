package com.travel.app.bus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Data
@AllArgsConstructor
@Builder
public class DriverDto {
    @NonNull
    private String driversLicense;

    @NonNull
    private String name;

    private String contact;
}
