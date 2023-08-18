package tda.darkarmy.housingsocietymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.housingsocietymanagement.exception.ResourceNotFoundException;
import tda.darkarmy.housingsocietymanagement.model.Notification;
import tda.darkarmy.housingsocietymanagement.repository.NotificationRepository;
import tda.darkarmy.housingsocietymanagement.repository.UserRepository;

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
        return notificationRepository.save(notification);
    }
}
