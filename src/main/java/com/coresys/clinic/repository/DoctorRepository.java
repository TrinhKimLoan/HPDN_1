// src/main/java/com/coresys/clinic/repository/DoctorRepository.java
package com.coresys.clinic.repository;

import com.coresys.clinic.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

    // Phương thức tìm kiếm bác sĩ theo họ tên (không phân biệt hoa thường)
    List<Doctor> findByFullNameContainingIgnoreCase(String fullName);

    // Phương thức tìm kiếm bác sĩ theo chuyên khoa (không phân biệt hoa thường)
    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);

}