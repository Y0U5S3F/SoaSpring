package com.example.devoir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.devoir.model.Auteur;

@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
}
