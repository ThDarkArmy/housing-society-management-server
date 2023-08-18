package tda.darkarmy.housingsocietymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.housingsocietymanagement.exception.ResourceNotFoundException;
import tda.darkarmy.housingsocietymanagement.model.Flat;
import tda.darkarmy.housingsocietymanagement.model.User;
import tda.darkarmy.housingsocietymanagement.model.Visitor;
import tda.darkarmy.housingsocietymanagement.repository.FlatRepository;
import tda.darkarmy.housingsocietymanagement.repository.UserRepository;
import tda.darkarmy.housingsocietymanagement.repository.VisitorRepository;

import java.util.List;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlatRepository flatRepository;

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor getVisitorById(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
    }

    public Visitor createVisitor(Visitor visitor) {
        Flat flat = flatRepository.findById(visitor.getFlatId()).orElseThrow(()-> new ResourceNotFoundException("Flat not found"));
        //User user = userRepository.findById()

        return visitorRepository.save(visitor);
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

