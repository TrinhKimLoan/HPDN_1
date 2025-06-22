// src/main/java/com/coresys/clinic.dto/DoctorSearchDto.java
package com.coresys.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSearchDto {
    private String id;
    private String fullName;
    private String specialization;
    private String title;
    private int experienceYears;
}