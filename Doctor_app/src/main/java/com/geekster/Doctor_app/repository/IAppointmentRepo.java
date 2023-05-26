package com.geekster.Doctor_app.repository;

import com.geekster.Doctor_app.models.Appointment;
import com.geekster.Doctor_app.models.AppointmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentRepo extends JpaRepository<Appointment, AppointmentKey> {
}
