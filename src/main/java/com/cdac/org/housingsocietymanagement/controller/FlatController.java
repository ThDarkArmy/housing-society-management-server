package com.cdac.org.housingsocietymanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cdac.org.housingsocietymanagement.model.Flat;
import com.cdac.org.housingsocietymanagement.service.FlatService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FlatController {

    @Autowired
    private FlatService flatService;

    @GetMapping
    public ResponseEntity<List<Flat>> getAllFlats() {
        List<Flat> flats = flatService.getAllFlats();
        return new ResponseEntity<>(flats, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flat> getFlatById(@PathVariable Long id) {
        Flat flat = flatService.getFlatById(id);
        return new ResponseEntity<>(flat, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Flat> createFlat(@PathVariable Long id, @RequestBody Flat flat) {
        Flat newFlat = flatService.createFlat(id, flat);
        return new ResponseEntity<>(newFlat, HttpStatus.CREATED);
    }

    @PostMapping("/book-flat/{id}")
    public ResponseEntity<Flat> bookFlat(@PathVariable Long id) {
        Flat newFlat = flatService.bookFlat(id);
        return new ResponseEntity<>(newFlat, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flat> updateFlat(
            @PathVariable Long id,
            @RequestBody Flat flat) {
        Flat updatedFlat = flatService.updateFlat(id, flat);
        return new ResponseEntity<>(updatedFlat, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFlat(@PathVariable Long id) {
        String message = flatService.deleteFlat(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

