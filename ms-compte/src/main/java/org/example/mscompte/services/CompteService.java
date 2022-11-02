package org.example.mscompte.services;

import org.example.mscompte.dto.CompteDetailResponseDTO;
import org.example.mscompte.dto.CompteRequestDTO;
import org.example.mscompte.dto.CompteResponseDTO;

import java.util.List;

public interface CompteService {

    List<CompteResponseDTO> getAllComptes();
    List<CompteResponseDTO> getAllComptesByName(String name);

    CompteDetailResponseDTO getCompteById(String idCompte);

    CompteDetailResponseDTO updateCompte(CompteRequestDTO compteRequestDTO,String idCompte);
    CompteDetailResponseDTO saveCompte(CompteRequestDTO compteRequestDTO);
    String deleteCompte(String idCompte);

    List<CompteResponseDTO> getFollowers(String idCompte);
    List<CompteResponseDTO> getFollowings(String idCompte);
    String unFollowing(String idCompte , String idCompteFollowing);
    String addFollowing(String idCompte,String idCompteFollowing);



}
