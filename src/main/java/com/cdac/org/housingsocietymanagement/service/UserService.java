package com.cdac.org.housingsocietymanagement.service;


import com.cdac.org.housingsocietymanagement.config.JwtTokenProvider;
import com.cdac.org.housingsocietymanagement.dto.LoginResponse;
import com.cdac.org.housingsocietymanagement.dto.UserDto;
import com.cdac.org.housingsocietymanagement.exception.ResourceNotFoundException;
import com.cdac.org.housingsocietymanagement.exception.UserAlreadyExistsException;
import com.cdac.org.housingsocietymanagement.model.User;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cdac.org.housingsocietymanagement.dto.LoginRequest;
import com.cdac.org.housingsocietymanagement.dto.PasswordResetRequest;
import com.cdac.org.housingsocietymanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MailSenderService mailSenderService;

    public User signup(UserDto userDto) throws MessagingException {

        User user = userRepository.findByEmail(userDto.getEmail());
        if(user!=null && user.getVerified()) throw new UserAlreadyExistsException("User with given email already exists");

        if(user!=null) userRepository.deleteById(user.getId());

        user = new User();

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setContactNumber(userDto.getContactNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAddress(userDto.getAddress());
        user.setVerified(userDto.getVerified());

        Long otp = new Random().nextLong(9999 - 1000 + 1) + 1000;
        user.setOtp(otp);

        mailSenderService.send(user, otp.toString());
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest){
        User user =  userRepository.findByEmail(loginRequest.getEmail());

        if(user==null) throw new ResourceNotFoundException("User with given email not found.");
        if(!user.getVerified()) throw new ResourceNotFoundException("User is not verified yet.");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(token, user);
    }

    public String verifyOtp(String email, Long otp){
        User user =  userRepository.findByEmail(email);
        System.out.println(user.getOtp()+" "+otp);
        if(user.getOtp().longValue()==otp.longValue()){
            user.setVerified(true);
            userRepository.save(user);
            return "Otp verified successfully";
        }else{
            return "Invalid otp";
        }
    }

    public User updateUser(UserDto userDto, Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setContactNumber(userDto.getContactNumber());
        user.setRole(userDto.getRole());
        user.setAddress(userDto.getAddress());
        return userRepository.save(user);
    }

    public User changePassword(PasswordResetRequest resetRequest){

        User user = userRepository.findByEmail(resetRequest.getEmail());
        if(user == null){
            throw new ResourceNotFoundException("No user found with given email.");
        }
        user.setPassword(passwordEncoder.encode(resetRequest.getPassword()));
        return userRepository.save(user);
    }


    public User getLoggedInUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("User Details:  "+userDetails.getUsername());
        return userRepository.findByEmail(userDetails.getUsername());
    }

    public Optional<User> getUser(Long userId){
        return userRepository.findById(userId);
    }

    public String deleteUser(){
        User user = getLoggedInUser();
        userRepository.deleteById(user.getId());

        return "User deleted successfully";
    }

    public String deleteById(Long id) {
        if(getUser(id).isEmpty()) throw new ResourceNotFoundException("User not found");
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted successfully";
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void createUser(UserDto userDto){

        User user = userRepository.findByEmail(userDto.getEmail());
        if(user==null) {
            user = new User();

            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            user.setContactNumber(userDto.getContactNumber());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setAddress(userDto.getAddress());

            userRepository.save(user);
        }

    }
}
