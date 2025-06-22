// src/main/java/com/coresys/clinic/repository/DoctorRepository.java
package com.coresys.clinic.repository;
import com.coresys.clinic.dto.DoctorSearchDto;
import com.coresys.clinic.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

    // --- Optimized Search (for "After Optimization") ---
    // Tìm theo tên, sử dụng Projection
    @Query(value = "{ 'fullName' : { '$regex' : ?0, '$options' : 'i' } }",
            fields = "{ 'fullName' : 1, 'specialization' : 1, 'title' : 1, 'experienceYears' : 1, '_id' : 1 }")
    List<DoctorSearchDto> searchDoctorsByFullNameOptimized(String fullName);

    // Tìm theo chuyên khoa, sử dụng Projection
    @Query(value = "{ 'specialization' : { '$regex' : ?0, '$options' : 'i' } }",
            fields = "{ 'fullName' : 1, 'specialization' : 1, 'title' : 1, 'experienceYears' : 1, '_id' : 1 }")
    List<DoctorSearchDto> searchDoctorsBySpecializationOptimized(String specialization);

}