package com.geekster.Doctor_app.service;

import com.geekster.Doctor_app.dto.SignInInput;
import com.geekster.Doctor_app.dto.SignInOutput;
import com.geekster.Doctor_app.dto.SignUpInput;
import com.geekster.Doctor_app.dto.SignUpOutput;
import com.geekster.Doctor_app.models.AppointmentKey;
import com.geekster.Doctor_app.models.AuthenticationToken;
import com.geekster.Doctor_app.models.Doctor;
import com.geekster.Doctor_app.models.Patient;
import com.geekster.Doctor_app.repository.IPatientRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatientRepo iPatientRepo;

    @Autowired
    AuthenticationService tokenService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;
    public SignUpOutput signup(SignUpInput signUpDto) {

        //check if user exist or not based on email
        Patient patient=iPatientRepo.findFirstByPatientEmail(signUpDto.getUserEmail());

        if(patient!=null){
            throw new IllegalStateException("patient already exist!!!...sign in instead");
        }
        String encryptedPassword=null;
        //Encryption
        try {
             encryptedPassword=encryptPassword(signUpDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //save the user

        patient = new Patient(signUpDto.getUserFirstName(),
                signUpDto.getUserLastName(),signUpDto.getUserEmail(),
                encryptedPassword, signUpDto.getUserContact());

        iPatientRepo.save(patient);

        // create token and save token
        AuthenticationToken token = new AuthenticationToken(patient);

        tokenService.saveToken(token);

        return new SignUpOutput("Patient registered","Patient created successfully");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();
        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signIn(SignInInput signInDto) {

        //get email

        Patient patient = iPatientRepo.findFirstByPatientEmail(signInDto.getPatientEmail());

        if(patient == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }
        //encrypt the password

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPatientPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(patient.getPatientPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }
        //figure out the token

        AuthenticationToken authToken = tokenService.getToken(patient);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!",authToken.getToken());


    }

    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    public void cancelAppointment(AppointmentKey key) {
        appointmentService.cancelAppointment(key);
    }
}
