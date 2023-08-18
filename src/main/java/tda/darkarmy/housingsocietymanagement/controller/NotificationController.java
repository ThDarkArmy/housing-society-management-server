package tda.darkarmy.housingsocietymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.housingsocietymanagement.model.Notification;
import tda.darkarmy.housingsocietymanagement.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAll();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.getOne(id);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification newNotification = notificationService.create(notification);
        return new ResponseEntity<>(newNotification, HttpStatus.CREATED);
    }
}

