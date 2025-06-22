// src/main/java/com/coresys/clinic.service/DoctorService.java
package com.coresys.clinic.service;

import com.coresys.clinic.model.Doctor;
import com.coresys.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional; // Import này cần cho findById

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Phương thức tìm kiếm ban đầu
    public List<Doctor> searchDoctorsInitial(String searchTerm) {
        // Tìm kiếm theo tên HOẶC chuyên khoa.
        return doctorRepository.findByFullNameContainingIgnoreCaseOrSpecializationContainingIgnoreCase(searchTerm, searchTerm);
    }

    // --- Phương thức Create (Thêm mới) ---
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // --- Phương thức Read by ID (Đọc chi tiết 1 bác sĩ) ---
    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    // --- Phương thức Update (Cập nhật) ---
    public Doctor updateDoctor(String id, Doctor doctorDetails) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            // Cập nhật các trường dữ liệu
            existingDoctor.setFullName(doctorDetails.getFullName());
            existingDoctor.setExperienceYears(doctorDetails.getExperienceYears());
            existingDoctor.setDateOfBirth(doctorDetails.getDateOfBirth());
            existingDoctor.setDegree(doctorDetails.getDegree());
            existingDoctor.setSpecialization(doctorDetails.getSpecialization());
            existingDoctor.setTitle(doctorDetails.getTitle());
            existingDoctor.setPhoneNumber(doctorDetails.getPhoneNumber());
            existingDoctor.setEmail(doctorDetails.getEmail());
            existingDoctor.setActive(doctorDetails.isActive()); // Cập nhật trạng thái

            return doctorRepository.save(existingDoctor);
        } else {
            return null;
        }
    }

    // --- Phương thức Delete (Xóa) ---
    public boolean deleteDoctor(String id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false; // Không tìm thấy bác sĩ để xóa
    }

    // --- Phương thức đọc tất cả bác sĩ (thường dùng cho danh sách quản lý) ---
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}