package com.cdac.org.housingsocietymanagement.service;

import com.cdac.org.housingsocietymanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdac.org.housingsocietymanagement.model.Flat;
import com.cdac.org.housingsocietymanagement.model.Notification;
import com.cdac.org.housingsocietymanagement.model.User;
import com.cdac.org.housingsocietymanagement.model.Visitor;
import com.cdac.org.housingsocietymanagement.repository.FlatRepository;
import com.cdac.org.housingsocietymanagement.repository.UserRepository;
import com.cdac.org.housingsocietymanagement.repository.VisitorRepository;

import java.util.List;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private NotificationService notificationService;

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor getVisitorById(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
    }

    public Visitor createVisitor(Visitor visitor) {
        Flat flat = flatRepository.findById(visitor.getFlatId()).orElseThrow(()-> new ResourceNotFoundException("Flat not found"));
        User user = userRepository.findById(flat.getUser().getId()).orElseThrow();
        Visitor visitor1 = visitorRepository.save(visitor);
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(visitor.getName() + " is here to see you. You can call him on: "+visitor.getContactNumber());
        notification.setDetail(visitor1.toString());

        notificationService.create(notification);
        return visitor1;
    }

    public Visitor updateVisitor(Long id, Visitor visitor) {
        Visitor existingVisitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

        existingVisitor.setName(visitor.getName());
        existingVisitor.setEmail(visitor.getEmail());
        existingVisitor.setContactNumber(visitor.getContactNumber());
        existingVisitor.setAddress(visitor.getAddress());
        existingVisitor.setFlatId(visitor.getFlatId());

        return visitorRepository.save(existingVisitor);
    }

    public String deleteVisitor(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));

        visitorRepository.delete(visitor);
        return "Visitor deleted successfully";
    }
}

