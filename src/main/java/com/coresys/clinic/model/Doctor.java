// src/main/java/com/coresys/clinic/model/Doctor.java
package com.coresys.clinic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Objects; // Cần thiết cho equals/hashCode nếu bạn muốn tự viết

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

    // --- Constructors ---

    // Constructor không đối số (NoArgsConstructor tương đương)
    public Doctor() {
    }

    // Constructor có tất cả các đối số (AllArgsConstructor tương đương)
    public Doctor(String fullName, int experienceYears, LocalDate dateOfBirth, String degree,
                  String specialization, String title, String phoneNumber, String email, boolean isActive) {
        this.fullName = fullName;
        this.experienceYears = experienceYears;
        this.dateOfBirth = dateOfBirth;
        this.degree = degree;
        this.specialization = specialization;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isActive = isActive;
    }

    // --- Getters ---

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDegree() {
        return degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getTitle() {
        return title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    // --- Setters ---

    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}