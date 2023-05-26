package com.geekster.Doctor_app.repository;

import com.geekster.Doctor_app.models.AuthenticationToken;
import com.geekster.Doctor_app.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findByPatient(Patient patient);

    AuthenticationToken findFirstByToken(String token);
}
