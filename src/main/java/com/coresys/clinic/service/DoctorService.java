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

    // Phương thức tìm kiếm ban đầu (dùng cho Before Optimization)
    public List<Doctor> searchDoctorsInitial(String searchTerm) {
        // Tìm kiếm theo tên HOẶC chuyên khoa.
        // Giả định rằng MongoDB chưa có index trên các trường này.
        return doctorRepository.findByFullNameContainingIgnoreCaseOrSpecializationContainingIgnoreCase(searchTerm, searchTerm);
    }

    // Các phương thức khác (ví dụ: thêm, sửa, xóa bác sĩ) có thể được thêm vào đây
    // --- Phương thức Create (Thêm mới) ---
    public Doctor createDoctor(Doctor doctor) {
        // Có thể thêm logic kiểm tra trùng lặp (ví dụ: email, phoneNumber) ở đây
        // Hoặc các logic nghiệp vụ khác trước khi lưu
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
            // ... cập nhật tất cả các trường bạn muốn cho phép chỉnh sửa

            return doctorRepository.save(existingDoctor);
        } else {
            // Xử lý khi không tìm thấy bác sĩ để cập nhật
            // Có thể throw exception hoặc trả về null/Optional.empty()
            return null; // Đơn giản là trả về null trong ví dụ này
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