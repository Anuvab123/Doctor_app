package com.geekster.Doctor_app.repository;

import com.geekster.Doctor_app.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepo extends JpaRepository<Doctor, Long> {
    Doctor findByDoctorId(Long docId);
}
