package com.cdac.org.housingsocietymanagement.service;


import com.cdac.org.housingsocietymanagement.exception.ResourceNotFoundException;
import com.cdac.org.housingsocietymanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdac.org.housingsocietymanagement.model.Announcement;
import com.cdac.org.housingsocietymanagement.model.Building;
import com.cdac.org.housingsocietymanagement.repository.AnnouncementRepository;
import com.cdac.org.housingsocietymanagement.repository.BuildingRepository;

import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserService userService;

    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    public Announcement getAnnouncementById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));
    }

    public List<Announcement> getMyAnnouncements() {
        User user = userService.getLoggedInUser();
        Building building = buildingRepository.findByUser(user);
        return announcementRepository.findByBuilding(building);
    }

    public Announcement createAnnouncement(Announcement announcement) {
        User user = userService.getLoggedInUser();
        Building building = buildingRepository.findByUser(user);
        announcement.setBuilding(building);
        return announcementRepository.save(announcement);
    }

    public Announcement updateAnnouncement(Long id, Announcement announcement) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));
        existingAnnouncement.setTitle(announcement.getTitle());
        existingAnnouncement.setDetail(announcement.getDetail());
        existingAnnouncement.setBuilding(existingAnnouncement.getBuilding());
        return announcementRepository.save(existingAnnouncement);
    }

    public String deleteAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));

        announcementRepository.delete(announcement);
        return "Announcement deleted successfully";
    }
}

