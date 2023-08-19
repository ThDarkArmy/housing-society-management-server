package com.cdac.org.housingsocietymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdac.org.housingsocietymanagement.exception.ResourceNotFoundException;
import com.cdac.org.housingsocietymanagement.model.Notification;
import com.cdac.org.housingsocietymanagement.repository.NotificationRepository;
import com.cdac.org.housingsocietymanagement.repository.UserRepository;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<Notification> getAll(){
        return notificationRepository.findByUser(userService.getLoggedInUser());
    }

    public Notification getOne(Long id){
        return notificationRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Notification not found"));
    }

    public Notification create(Notification notification){
        Notification notification1 = notificationRepository.save(notification);
        //System.out.println("\n\n\nnotification: "+ notification1 );
        return notification1;
    }
}
