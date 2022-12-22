package com.travel.app.bookings.pojo;

import jakarta.persistence.*;
import lombok.*;




import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal costOfTrip;
    private String destination;
    @ElementCollection
    private List<Long> seatNumber ;
    @ElementCollection
    private List<String> passengerName;
    private String busType;
    

}
