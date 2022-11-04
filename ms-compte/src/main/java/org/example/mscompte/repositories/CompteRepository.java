package org.example.mscompte.repositories;

import org.example.mscompte.entities.Compte;
import org.example.mscompte.enums.CompteType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte,String> {
    List<Compte> findByNomContainsAndType(String nom, CompteType type);
    List<Compte> findAllByFollowingsContains(Compte comptefollowing);


    @Query("select c from Compte c where c.type='PUBLIC' and  c.companyName like %?1%")
    Page<Compte> findByNomContains(String nom,Pageable pageable);

    Page<Compte> findAllByFollowingsContains(Compte comptefollowing,Pageable pageable);


}
