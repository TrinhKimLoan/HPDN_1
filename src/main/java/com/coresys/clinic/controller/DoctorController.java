// src/main/java/com/coresys/clinic.controller/DoctorController.java
package com.coresys.clinic.controller;

import com.coresys.clinic.model.Doctor;
import com.coresys.clinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus; // Import này cần cho ResponseEntity
import org.springframework.http.ResponseEntity; // Import này cần cho ResponseEntity
import org.springframework.web.bind.annotation.*; // Import tất cả các annotations @GetMapping, @PostMapping,...

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // --- Endpoints Read FindbyName ---
    // GET http://localhost:8080/api/doctors/search/initial/by-name?query=Nguyễn
    @GetMapping("/search/initial/by-name")
    public List<Doctor> searchDoctorsByFullNameInitial(@RequestParam String query) {
        return doctorService.searchDoctorsByFullNameInitial(query);
    }

    // Endpoint Read FindBySpecialization
    // GET http://localhost:8080/api/doctors/search/initial/by-specialization?query=Nhi
    @GetMapping("/search/initial/by-specialization")
    public List<Doctor> searchDoctorsBySpecializationInitial(@RequestParam String query) {
        return doctorService.searchDoctorsBySpecializationInitial(query);
    }

    // --- Endpoint để lấy tất cả bác sĩ ---
    // Ví dụ: GET http://localhost:8080/api/doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // --- Endpoint để lấy thông tin chi tiết một bác sĩ theo ID ---
    // Ví dụ: GET http://localhost:8080/api/doctors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok) // Nếu tìm thấy, trả về 200 OK
                .orElse(ResponseEntity.notFound().build()); // Nếu không tìm thấy, trả về 404 Not Found
    }

    // --- Endpoint Create (Thêm mới bác sĩ) ---
    // Ví dụ: POST http://localhost:8080/api/doctors
    // Body: JSON của đối tượng Doctor (như ví dụ JSON bạn dùng để seed data)
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED); // Trả về 201 Created
    }

    // --- Endpoint Update (Cập nhật thông tin bác sĩ) ---
    // Ví dụ: PUT http://localhost:8080/api/doctors/{id}
    // Body: JSON của đối tượng Doctor với thông tin cập nhật
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String id, @RequestBody Doctor doctorDetails) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorDetails);
        if (updatedDoctor != null) {
            return ResponseEntity.ok(updatedDoctor); // Trả về 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Trả về 404 Not Found nếu không tìm thấy ID
        }
    }

    // --- Endpoint Delete (Xóa bác sĩ) ---
    // Ví dụ: DELETE http://localhost:8080/api/doctors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Trả về 204 No Content nếu xóa thành công
        } else {
            return ResponseEntity.notFound().build(); // Trả về 404 Not Found nếu không tìm thấy ID
        }
    }
}