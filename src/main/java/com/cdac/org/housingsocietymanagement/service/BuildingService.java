package com.cdac.org.housingsocietymanagement.service;

import com.cdac.org.housingsocietymanagement.dto.BuildingDto;
import com.cdac.org.housingsocietymanagement.exception.ResourceNotFoundException;
import com.cdac.org.housingsocietymanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.cdac.org.housingsocietymanagement.model.Building;
import com.cdac.org.housingsocietymanagement.repository.BuildingRepository;
import com.cdac.org.housingsocietymanagement.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class BuildingService {
    private final String BASE_URL = "http://localhost:8000";
    private Path fileStoragePath;

    @Autowired
    private BuildingRepository buildingRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public BuildingService() {
        try {
            fileStoragePath = Paths.get("src\\main\\resources\\static\\fileStorage").toAbsolutePath().normalize();
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }
    }

    public List<Building> getAllBuildings(){
        return buildingRepository.findAll();
    }
    
    public Building getBuildingById(Long id){
        return buildingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Building not found"));
    }

    public Building getMyBuilding(){
        return buildingRepository.findByUser(userService.getLoggedInUser());
    }
    
    public Building assignSecretary(Long buildingId, Long userId){
        Building building = buildingRepository.findById(buildingId).orElseThrow(()-> new ResourceNotFoundException("Building not found"));
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException());
        user.setBuilding(building);
        userRepository.save(user);
        building.setUser(user);
        return buildingRepository.save(building);
    }
    
    public Building addBuilding(BuildingDto buildingDto){
//        User user = userService.getLoggedInUser();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(buildingDto.getImage().getOriginalFilename()));
        fileName = Math.random() + fileName.replace(" ", "");
        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);

        try {
            Files.copy(buildingDto.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Building building = new Building();
//        building.setUser(user);
        building.setAddress(buildingDto.getAddress());
        building.setImageUrl(BASE_URL + "/fileStorage/" + fileName);
        building.setName(buildingDto.getName());
        building.setNumberOfFlats(buildingDto.getNumberOfFlats());
        return buildingRepository.save(building);
    }
    
    public Building updateBuilding(Long id, Building building){
        Building building1 = buildingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Building not found"));
        building.setId(id);
        building.setImageUrl(building1.getImageUrl());
        return buildingRepository.save(building);
    }
    
    public String removeBuilding(Long id){
        buildingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Building not found"));
        buildingRepository.deleteById(id);
        return "Building deleted successfully";
    }
}
