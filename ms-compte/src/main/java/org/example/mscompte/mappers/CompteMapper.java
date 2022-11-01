package org.example.mscompte.mappers;

import org.example.mscompte.dto.CompteDetailResponseDTO;
import org.example.mscompte.dto.CompteRequestDTO;
import org.example.mscompte.dto.CompteResponseDTO;
import org.example.mscompte.entities.Compte;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompteMapper {
    CompteResponseDTO fromCompteToCompteResponseDTO(Compte compte);
    CompteDetailResponseDTO fromCompteToCompteDetailResponseDTO(Compte compte);
    Compte fromCompteRequestDTO(CompteRequestDTO compteRequestDTO);
}
