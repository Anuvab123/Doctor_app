package com.geekster.Doctor_app.service;

import com.geekster.Doctor_app.models.Appointment;
import com.geekster.Doctor_app.models.AppointmentKey;
import com.geekster.Doctor_app.repository.IAppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    IAppointmentRepo appointmentRepo;

    public void bookAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
    }

    public void cancelAppointment(AppointmentKey key) {
    }
}
