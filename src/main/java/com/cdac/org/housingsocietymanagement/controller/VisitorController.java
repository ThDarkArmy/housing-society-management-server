package com.cdac.org.housingsocietymanagement.controller;

import com.cdac.org.housingsocietymanagement.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cdac.org.housingsocietymanagement.model.Visitor;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visitors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        List<Visitor> visitors = visitorService.getAllVisitors();
        return new ResponseEntity<>(visitors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable Long id) {
        Visitor visitor = visitorService.getVisitorById(id);
        return new ResponseEntity<>(visitor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Visitor> createVisitor(@RequestBody Visitor visitor) {
        Visitor newVisitor = visitorService.createVisitor(visitor);
        return new ResponseEntity<>(newVisitor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitor(
            @PathVariable Long id,
            @RequestBody Visitor visitor) {
        Visitor updatedVisitor = visitorService.updateVisitor(id, visitor);
        return new ResponseEntity<>(updatedVisitor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVisitor(@PathVariable Long id) {
        String message = visitorService.deleteVisitor(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
