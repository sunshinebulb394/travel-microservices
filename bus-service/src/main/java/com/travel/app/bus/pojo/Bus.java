package com.travel.app.bus.pojo;

import java.time.LocalDate;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Bus {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   private String busType;
   private String insuranceNumber;
   private String registrationNumber;
   private LocalDate purchaseDate;
   private String insuranceCompany;
   private LocalDate insuranceExpiryDate;
   private LocalDate roadWorthyExpiryDate;
   private Long capacity;
   private String isAvailable;
   @Builder.Default
   private LocalDate dateAdded = LocalDate.now();
   
 
   
   
   
   
}