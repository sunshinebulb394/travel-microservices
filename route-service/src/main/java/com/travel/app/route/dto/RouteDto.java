package com.travel.app.route.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RouteDto {
    private String startPoint;
    private String endPoint;
    private List<String> checkPoint;
}
