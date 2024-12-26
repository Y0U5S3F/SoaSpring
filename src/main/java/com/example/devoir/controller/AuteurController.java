package com.example.devoir.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.devoir.model.Auteur;
import com.example.devoir.service.AuteurService;

@RestController
@RequestMapping(path = "api/Auteur")
public class AuteurController {
	
    private final AuteurService auteurService;

    @Autowired
    public AuteurController(AuteurService auteurService) {
        this.auteurService = auteurService;
    }
    
    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return auteurService.getAllAuteurs();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        Auteur auteur = auteurService.getById(id);
        if (auteur != null) {
            return new ResponseEntity<>(auteur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PostMapping
    public ResponseEntity<Auteur> addAuteur(@RequestBody Auteur auteur) {
        Auteur newAuteur = auteurService.addAuteur(auteur);
        return new ResponseEntity<>(newAuteur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Long id, @RequestBody Auteur auteur) {
        Optional<Auteur> existingAuteur = auteurService.getAllAuteurs().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
        
        if (existingAuteur.isPresent()) {
            Auteur updatedAuteur = auteurService.modifierAuteur(id, auteur);
            return new ResponseEntity<>(updatedAuteur, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
     

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAuteur(@PathVariable Long id) {
        boolean isDeleted = auteurService.suppAuteur(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
