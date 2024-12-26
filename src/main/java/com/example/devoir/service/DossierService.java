package com.example.devoir.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devoir.model.Dossier;
import com.example.devoir.repository.DossierRepository;

@Service
public class DossierService {

	private final DossierRepository dossierRepository;
	
	@Autowired
	public DossierService(DossierRepository dossierRepository) {
		this.dossierRepository = dossierRepository;
	}
	
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    public Dossier getById(Long id) {
        return dossierRepository.findById(id).orElse(null);
    }

    public Dossier addDossier(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    public Dossier modifierDossier(Long id, Dossier dossier) {
        Optional<Dossier> oldDossierOpt = dossierRepository.findById(id);
        if (oldDossierOpt.isPresent()) {
            Dossier oldDossier = oldDossierOpt.get();
            oldDossier.setNom(dossier.getNom() != null ? dossier.getNom() : oldDossier.getNom());
            oldDossier.setDescription(dossier.getDescription() != null ? dossier.getDescription() : oldDossier.getDescription());
            oldDossier.setDateCreation(dossier.getDateCreation() != null ? dossier.getDateCreation() : oldDossier.getDateCreation());
            oldDossier.setResponsable(dossier.getResponsable() != null ? dossier.getResponsable() : oldDossier.getResponsable());
            oldDossier.setDocuments(dossier.getDocuments() != null ? dossier.getDocuments() : oldDossier.getDocuments());
            return dossierRepository.save(oldDossier);
        } else {
            return null;
        }
    }

    public boolean suppDossier(Long id) {
        if (dossierRepository.existsById(id)) {
            dossierRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
