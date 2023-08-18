package tda.darkarmy.housingsocietymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tda.darkarmy.housingsocietymanagement.dto.BuildingDto;
import tda.darkarmy.housingsocietymanagement.model.Building;
import tda.darkarmy.housingsocietymanagement.service.BuildingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buildings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public ResponseEntity<List<Building>> getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id) {
        Building building = buildingService.getBuildingById(id);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @PostMapping("/{buildingId}/assign-secretary/{userId}")
    public ResponseEntity<Building> assignSecretary(
            @PathVariable("buildingId") Long buildingId,
            @PathVariable("userId") Long userId) {
        Building building = buildingService.assignSecretary(buildingId, userId);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Building> addBuilding(@ModelAttribute BuildingDto buildingDto) {
        Building newBuilding = buildingService.addBuilding(buildingDto);
        return new ResponseEntity<>(newBuilding, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(
            @PathVariable Long id,
            @RequestBody Building building) {
        Building updatedBuilding = buildingService.updateBuilding(id, building);
        return new ResponseEntity<>(updatedBuilding, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeBuilding(@PathVariable Long id) {
        String message = buildingService.removeBuilding(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

