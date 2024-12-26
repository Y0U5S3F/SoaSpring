package com.example.devoir.controller;

import java.util.List;
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
import com.example.devoir.model.Dossier;
import com.example.devoir.service.DossierService;

@RestController
@RequestMapping(path = "api/Dossier")
public class DossierController {
	private final DossierService dossierService;

	@Autowired
	public DossierController(DossierService dossierService){
		 this.dossierService = dossierService;

	}
	
    @GetMapping
    public ResponseEntity<List<Dossier>> getAllDossiers() {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dossier> getDossierById(@PathVariable Long id) {
        Dossier dossier = dossierService.getById(id);
        if (dossier != null) {
            return new ResponseEntity<>(dossier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Dossier> addDossier(@RequestBody Dossier dossier) {
        Dossier newDossier = dossierService.addDossier(dossier);
        return new ResponseEntity<>(newDossier, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dossier> updateDossier(@PathVariable Long id, @RequestBody Dossier dossier) {
        Dossier updatedDossier = dossierService.modifierDossier(id, dossier);
        if (updatedDossier != null) {
            return new ResponseEntity<>(updatedDossier, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossier(@PathVariable Long id) {
        boolean isDeleted = dossierService.suppDossier(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
