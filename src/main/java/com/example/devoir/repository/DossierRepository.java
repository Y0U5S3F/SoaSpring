package com.example.devoir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.devoir.model.Dossier;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {

}
