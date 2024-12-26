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

import com.example.devoir.model.Document;
import com.example.devoir.service.DocumentService;

@RestController
@RequestMapping(path = "api/Document")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
    
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
    

    @GetMapping("/auteur/{id}")
    public ResponseEntity<List<Document>> getDocumentsByAuteurId(@PathVariable("id") Long auteurId) {
        List<Document> documents = documentService.getDocumentsByAuteurId(auteurId);

        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
    
    @GetMapping("/dossier/{id}")
    public ResponseEntity<List<Document>> getDocumentsByDossierId(@PathVariable("id") Long dossierId) {
        List<Document> documents = documentService.getDocumentsByDossierId(dossierId);

        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Document> addDocument(@RequestBody Document document) {
        Document newDocument = documentService.addDocument(document);
        return new ResponseEntity<>(newDocument, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document document) {
        Document updatedDocument = documentService.modifierDocument(id, document);
        if (updatedDocument != null) {
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDocument(@PathVariable Long id) {
        boolean isDeleted = documentService.suppDocument(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}