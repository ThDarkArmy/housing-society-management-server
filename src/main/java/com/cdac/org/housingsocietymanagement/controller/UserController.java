package com.cdac.org.housingsocietymanagement.controller;

import com.cdac.org.housingsocietymanagement.dto.VerifyOtpDto;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cdac.org.housingsocietymanagement.dto.LoginRequest;
import com.cdac.org.housingsocietymanagement.dto.PasswordResetRequest;
import com.cdac.org.housingsocietymanagement.dto.UserDto;
import com.cdac.org.housingsocietymanagement.service.UserService;


import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getALl(){
        return status(200).body(userService.findAll());
    }

    @GetMapping("/logged-in-user")
    public ResponseEntity<?> getLoggedInUser(){
        return status(200).body(userService.getLoggedInUser());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDto userDto) throws MessagingException {
        return status(201).body(userService.signup(userDto));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto) {
        return status(201).body(userService.verifyOtp(verifyOtpDto.getEmail(), verifyOtpDto.getOtp()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        return status(200).body(userService.login(loginRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto userDto, @PathVariable("id") Long id){
        return status(200).body(userService.updateUser(userDto, id));
    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordResetRequest request){
        return status(200).body(userService.changePassword(request));
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(){
        return status(200).body(userService.deleteUser());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
        return status(200).body(userService.deleteById(id));
    }
}
