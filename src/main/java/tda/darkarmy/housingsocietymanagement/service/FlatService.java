package tda.darkarmy.housingsocietymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tda.darkarmy.housingsocietymanagement.exception.ResourceNotFoundException;
import tda.darkarmy.housingsocietymanagement.model.Building;
import tda.darkarmy.housingsocietymanagement.model.Flat;
import tda.darkarmy.housingsocietymanagement.repository.BuildingRepository;
import tda.darkarmy.housingsocietymanagement.repository.FlatRepository;
import tda.darkarmy.housingsocietymanagement.repository.UserRepository;

import java.util.List;

@Service
public class FlatService {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingRepository buildingRepository;

    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    public Flat getFlatById(Long id) {
        return flatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flat not found"));
    }

    public Flat createFlat(Long buildingId, Flat flat) {
        Building building = buildingRepository.findById(buildingId).orElseThrow(()-> new ResourceNotFoundException("Building not found"));
        flat.setBuilding(building);
        return flatRepository.save(flat);
    }

    public Flat bookFlat(Long id){
        Flat flat = flatRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Flat not found"));
        flat.setUser(userService.getLoggedInUser());
        flat.setStatus(true);
        Flat flat1 = flatRepository.save(flat);
        System.out.println("\n\n\nFlat: "+ flat1);
        return flat1;
    }

    public Flat updateFlat(Long id, Flat flat) {
        Flat existingFlat = flatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flat not found"));

        existingFlat.setFlatNumber(flat.getFlatNumber());
        existingFlat.setFloorNumber(flat.getFloorNumber());
        existingFlat.setFlatType(flat.getFlatType());
        existingFlat.setStatus(flat.getStatus());
        existingFlat.setUser(flat.getUser());
        existingFlat.setBuilding(existingFlat.getBuilding());

        return flatRepository.save(existingFlat);
    }

    public String deleteFlat(Long id) {
        Flat flat = flatRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flat not found"));
        flatRepository.delete(flat);
        return "Flat deleted successfully";
    }
}

