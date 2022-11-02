package org.example.mscompte.repositories;

import org.example.mscompte.entities.Compte;
import org.example.mscompte.enums.CompteType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte,String> {
    List<Compte> findByNomContains(String nom);
    List<Compte> findByNomContainsAndType(String nom, CompteType type);
    List<Compte> findAllByFollowingsContains(Compte comptefollowing);

}
