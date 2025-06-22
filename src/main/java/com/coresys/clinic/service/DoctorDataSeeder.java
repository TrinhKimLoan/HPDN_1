// src/main/java/com/coresys/clinic/service/DoctorDataSeeder.java
package com.coresys.clinic.service;

import com.coresys.clinic.model.Doctor;
import com.coresys.clinic.repository.DoctorRepository;
import jakarta.annotation.PostConstruct; // Import này quan trọng
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component // Đảm bảo Spring tìm thấy và quản lý bean này
public class DoctorDataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DoctorDataSeeder.class);

    @Autowired
    private DoctorRepository doctorRepository;

    // @PostConstruct đảm bảo phương thức này chạy sau khi DoctorDataSeeder được khởi tạo và các dependencies được inject
    @PostConstruct
    public void seedDoctorData() {
        // Kiểm tra xem đã có dữ liệu chưa để tránh insert lặp lại mỗi khi ứng dụng khởi động
        if (doctorRepository.count() == 0) {
            log.info("Bắt đầu thêm dữ liệu bác sĩ mẫu vào MongoDB...");
            List<Doctor> doctors = new ArrayList<>();
            Random random = new Random();

            String[] firstNames = {"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Vũ", "Đặng", "Bùi"};
            String[] middleNames = {"Văn", "Thị", "Quang", "Minh", "Thu", "Ngọc", "Duy", "Hồng"};
            String[] lastNames = {"An", "Bình", "Cường", "Dung", "Hùng", "Lan", "Nam", "Quân", "Tâm", "Uyên"};
            String[] specializations = {"Tim mạch", "Nội tổng quát", "Nhi khoa", "Da liễu", "Răng Hàm Mặt", "Mắt", "Tai Mũi Họng", "Sản phụ khoa", "Chấn thương chỉnh hình", "Thần kinh"};
            String[] degrees = {"Thạc sĩ Y học", "Bác sĩ Chuyên khoa I", "Bác sĩ Chuyên khoa II", "Tiến sĩ Y học"};
            String[] titles = {"Bác sĩ", "Bác sĩ chính", "Trưởng khoa", "Phó khoa"};

            for (int i = 0; i < 150; i++) { // Thêm 150 bác sĩ
                String fullName = firstNames[random.nextInt(firstNames.length)] + " " +
                        middleNames[random.nextInt(middleNames.length)] + " " +
                        lastNames[random.nextInt(lastNames.length)];
                int experienceYears = 5 + random.nextInt(20); // 5 đến 24 năm kinh nghiệm
                LocalDate dateOfBirth = LocalDate.of(1960 + random.nextInt(30), 1 + random.nextInt(12), 1 + random.nextInt(28));
                String degree = degrees[random.nextInt(degrees.length)];
                String specialization = specializations[random.nextInt(specializations.length)];
                String title = titles[random.nextInt(titles.length)];
                String phoneNumber = "0" + (random.nextInt(900000000) + 100000000); // 10 chữ số
                String email = fullName.toLowerCase().replace(" ", "") + i + "@coresys.com";

                // Thay đổi ở đây: Sử dụng constructor không đối số và setters
                Doctor doctor = new Doctor(); // <-- Gọi constructor không đối số
                doctor.setFullName(fullName);
                doctor.setExperienceYears(experienceYears);
                doctor.setDateOfBirth(dateOfBirth);
                doctor.setDegree(degree);
                doctor.setSpecialization(specialization);
                doctor.setTitle(title);
                doctor.setPhoneNumber(phoneNumber);
                doctor.setEmail(email);
                doctor.setActive(true); // <-- Sử dụng setter của isActive
                doctors.add(doctor);
            }

            doctorRepository.saveAll(doctors);
            log.info("Đã thêm thành công {} bác sĩ mẫu vào MongoDB.", doctors.size());
        } else {
            log.info("Dữ liệu bác sĩ mẫu đã tồn tại trong MongoDB. Bỏ qua việc seeding.");
        }
    }
}