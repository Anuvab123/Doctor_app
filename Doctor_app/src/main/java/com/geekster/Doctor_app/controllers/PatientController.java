package com.geekster.Doctor_app.controllers;

import com.geekster.Doctor_app.dto.SignInInput;
import com.geekster.Doctor_app.dto.SignInOutput;
import com.geekster.Doctor_app.dto.SignUpInput;
import com.geekster.Doctor_app.dto.SignUpOutput;
import com.geekster.Doctor_app.models.AppointmentKey;
import com.geekster.Doctor_app.models.Doctor;
import com.geekster.Doctor_app.service.AuthenticationService;
import com.geekster.Doctor_app.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientService patientService;
    @Autowired
    AuthenticationService authService;

    //signup
    @PostMapping("/signup")
    public SignUpOutput signup(@RequestBody SignUpInput signUpInput){
        return patientService.signup(signUpInput);
    }

    @PostMapping("/signin")
    public SignInOutput signin(@RequestBody SignInInput signInDto)
    {
        return patientService.signIn(signInDto);
    }

    @GetMapping("/doctors/")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam String userEmail,@RequestParam String token){
        HttpStatus status;
        List<Doctor> allDoctors=null;

        if(authService.authenticate(userEmail,token)){
            allDoctors=patientService.getAllDoctors();
            status=HttpStatus.OK;
        }else{
            status=HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<List<Doctor>>(allDoctors, status);
    }

    //delete my appointment

    @DeleteMapping()
    ResponseEntity<Void> cancelAppointment(@RequestParam String userEmail, @RequestParam String token, @RequestBody AppointmentKey key){
        HttpStatus status;
        if(authService.authenticate(userEmail,token)){
            patientService.cancelAppointment(key);
            status=HttpStatus.OK;
        }else{
            status=HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<Void>(status);
    }
}
