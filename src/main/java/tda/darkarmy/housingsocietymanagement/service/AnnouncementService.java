package tda.darkarmy.housingsocietymanagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.housingsocietymanagement.exception.ResourceNotFoundException;
import tda.darkarmy.housingsocietymanagement.model.Announcement;
import tda.darkarmy.housingsocietymanagement.model.Building;
import tda.darkarmy.housingsocietymanagement.model.User;
import tda.darkarmy.housingsocietymanagement.repository.AnnouncementRepository;
import tda.darkarmy.housingsocietymanagement.repository.BuildingRepository;

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
        existingAnnouncement.setBuilding(announcement.getBuilding());

        return announcementRepository.save(existingAnnouncement);
    }

    public String deleteAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found"));

        announcementRepository.delete(announcement);
        return "Announcement deleted successfully";
    }
}

