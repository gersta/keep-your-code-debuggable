package de.gerritstapper.debuggable.controller;

import de.gerritstapper.debuggable.service.IDatabaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final IDatabaseService databaseService;

    public PersonController(IDatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/person/{id}")
    public ResponseEntity getPerson(@PathVariable("id") Long id) {
        String person = databaseService.getById(id);

        return ResponseEntity.ok(person);
    }
}
