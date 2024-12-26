package com.example.devoir.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import com.example.devoir.model.Auteur;
import com.example.devoir.repository.AuteurRepository;

@Service
public class AuteurService {

	private final AuteurRepository auteurRepository;
	
	@Autowired
	public AuteurService(AuteurRepository auteurRepository) {
		this.auteurRepository = auteurRepository ;
	}
	
    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }
    
    public Auteur getById(Long id) {
        return auteurRepository.findById(id).orElse(null);  // Returns null if not found
    }
    
    public Auteur addAuteur(Auteur auteur) {
    	return auteurRepository.save(auteur);
    }
    
    public Auteur modifierAuteur(Long id, Auteur auteur) {
    	Auteur oldAuteur = auteurRepository.findById(id).get();
    	oldAuteur.setNom(auteur.getNom() != null ? auteur.getNom() : oldAuteur.getNom());
    	oldAuteur.setPrenom(auteur.getPrenom() != null ? auteur.getPrenom() : oldAuteur.getPrenom());
    	oldAuteur.setEmail(auteur.getEmail() !=null? auteur.getEmail():oldAuteur.getEmail());
    	oldAuteur.setTelephone(auteur.getTelephone() != null ? auteur.getTelephone() : oldAuteur.getTelephone());
    	return auteurRepository.save(oldAuteur);
    }
    
    public boolean suppAuteur(Long id) {
    	auteurRepository.deleteById(id);
    	return auteurRepository.existsById(id);
    }
    
}
