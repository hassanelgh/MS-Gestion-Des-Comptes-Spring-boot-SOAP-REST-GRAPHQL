package org.example.mscompte.services;

import org.example.mscompte.dto.CompteDetailResponseDTO;
import org.example.mscompte.dto.CompteRequestDTO;
import org.example.mscompte.dto.CompteResponseDTO;
import org.example.mscompte.dto.ComptesResponseDTOPage;

import java.util.List;

public interface CompteService {

    List<CompteResponseDTO> getAllComptes();

    ComptesResponseDTOPage getAllComptesByName(String name, int pageCurrent, int size);

    CompteDetailResponseDTO getCompteById(String idCompte);

    CompteDetailResponseDTO updateCompte(CompteRequestDTO compteRequestDTO,String idCompte);
    CompteDetailResponseDTO saveCompte(CompteRequestDTO compteRequestDTO);
    String deleteCompte(String idCompte);

    ComptesResponseDTOPage getFollowers(String idCompte, int pageCurrent, int size);

    ComptesResponseDTOPage getFollowings(String idCompte, int pageCurrent, int size);

    String unFollowing(String idCompte , String idCompteFollowing);
    String addFollowing(String idCompte,String idCompteFollowing);



}
