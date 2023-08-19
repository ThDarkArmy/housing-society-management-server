package com.cdac.org.housingsocietymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cdac.org.housingsocietymanagement.model.Announcement;
import com.cdac.org.housingsocietymanagement.service.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcements")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/my-announcements")
    public ResponseEntity<List<Announcement>> getAllMyAnnouncements() {
        List<Announcement> announcements = announcementService.getMyAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        return new ResponseEntity<>(announcement, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        Announcement newAnnouncement = announcementService.createAnnouncement(announcement);
        return new ResponseEntity<>(newAnnouncement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Announcement> updateAnnouncement(
            @PathVariable Long id,
            @RequestBody Announcement announcement) {
        Announcement updatedAnnouncement = announcementService.updateAnnouncement(id, announcement);
        return new ResponseEntity<>(updatedAnnouncement, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Long id) {
        String message = announcementService.deleteAnnouncement(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

