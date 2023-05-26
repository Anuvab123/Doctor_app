package com.geekster.Doctor_app.service;

import com.geekster.Doctor_app.models.Appointment;
import com.geekster.Doctor_app.models.Doctor;
import com.geekster.Doctor_app.repository.IDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctorRepo doctorRepo;

    public void addDoc(Doctor doc) {
        doctorRepo.save(doc);
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> allDoctor=doctorRepo.findAll();
        return allDoctor;
    }

    public List<Appointment> getMyAppointments(Long docId) {
        Doctor myDoc=doctorRepo.findByDoctorId(docId);

        if(myDoc==null){
            throw new IllegalStateException("The doctor does not exist");
        }
        return myDoc.getAppointments();
    }
}
