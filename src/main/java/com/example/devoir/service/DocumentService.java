package com.example.devoir.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.devoir.model.Document;
import com.example.devoir.repository.DocumentRepository;

@Service
public class DocumentService {

	private final DocumentRepository documentRepository;
	
	@Autowired
	public DocumentService(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
	
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
	
    public Document getById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }
    
    public List<Document> getDocumentsByAuteurId(Long auteurId) {
        return documentRepository.findByAuteurId(auteurId);
    }
    
    public List<Document> getDocumentsByDossierId(Long dossierId) {
        return documentRepository.findByDossierId(dossierId);
    }
	
    public Document addDocument(Document document) {
        return documentRepository.save(document);
    }
	
    public Document modifierDocument(Long id, Document document) {
        Document oldDocument = documentRepository.findById(id).get();
        oldDocument.setTitre(document.getTitre() != null ? document.getTitre() : oldDocument.getTitre());
        oldDocument.setContenu(document.getContenu() != null ? document.getContenu() : oldDocument.getContenu());
        oldDocument.setDateCreation(document.getDateCreation() != null ? document.getDateCreation() : oldDocument.getDateCreation());
        oldDocument.setAuteur(document.getAuteur() != null ? document.getAuteur() : oldDocument.getAuteur());
        oldDocument.setDossier(document.getDossier() != null ? document.getDossier() : oldDocument.getDossier());
        return documentRepository.save(oldDocument);
    }
	
    public boolean suppDocument(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
