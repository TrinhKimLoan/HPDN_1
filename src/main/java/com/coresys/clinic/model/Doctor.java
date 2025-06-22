// src/main/java/com/coresys/clinic/model/Doctor.java
package com.coresys.clinic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Thêm các annotations của Lombok:
import lombok.Data; // Tự động tạo getters, setters, equals, hashCode, toString
import lombok.NoArgsConstructor; // Tự động tạo constructor không đối số
import lombok.AllArgsConstructor; // Tự động tạo constructor có tất cả các đối số

import java.time.LocalDate;

@Data // <-- Thêm annotation này
@NoArgsConstructor // <-- Thêm annotation này
@AllArgsConstructor // <-- Thêm annotation này
@Document(collection = "doctors") // Map to 'doctors' collection in MongoDB
public class Doctor {
    @Id
    private String id; // MongoDB will generate ObjectId for this

    private String fullName;
    private int experienceYears;
    private LocalDate dateOfBirth;
    private String degree;
    private String specialization; // E.g., "Tim mạch", "Nội tổng quát", "Nhi"
    private String title; // E.g., "Trưởng khoa", "Bác sĩ chính"
    private String phoneNumber;
    private String email;
    private boolean isActive;

}