package com.geekster.Doctor_app.repository;

import com.geekster.Doctor_app.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepo extends JpaRepository<Patient,Long> {
    Patient findFirstByPatientEmail(String userEmail);
}
