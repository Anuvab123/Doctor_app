package com.geekster.Doctor_app.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serializable;
import java.time.LocalDateTime;
@Embeddable
public class AppointmentKey implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long appointmentId;
    public LocalDateTime time;
}
