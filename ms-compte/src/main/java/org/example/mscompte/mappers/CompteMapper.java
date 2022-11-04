package org.example.mscompte.mappers;

import org.example.mscompte.dto.CompteDetailResponseDTO;
import org.example.mscompte.dto.CompteRequestDTO;
import org.example.mscompte.dto.CompteResponseDTO;
import org.example.mscompte.dto.ComptesResponseDTOPage;
import org.example.mscompte.entities.Compte;
import org.example.mscompte.enums.CompteType;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CompteMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;


    public abstract CompteResponseDTO fromCompteToCompteResponseDTO(Compte compte);
    public abstract CompteDetailResponseDTO fromCompteToCompteDetailResponseDTO(Compte compte);
    public abstract Compte fromCompteRequestDTO(CompteRequestDTO compteRequestDTO);

    public ComptesResponseDTOPage fromComptesPage(Page<Compte> page){
        List<Compte> comptes=page.getContent();

        return ComptesResponseDTOPage.builder()
                .page(page.getNumber())
                .totaleElements((int) page.getTotalElements())
                .totalePages(page.getTotalPages())
                .size(comptes.size())
                .compteResponseDTOS(comptes.stream().map(compte -> fromCompteToCompteResponseDTO(compte)).collect(Collectors.toList()))
                .build();

    }
    
    public void fromCompteRequestDTOToCompte (Compte compte,CompteRequestDTO compteRequestDTO){

        if(compteRequestDTO.getNom()!=null) compte.setNom(compteRequestDTO.getNom());
        if(compteRequestDTO.getDescription()!=null) compte.setDescription(compteRequestDTO.getDescription());
        if(compteRequestDTO.getUsername()!=null) compte.setUsername(compteRequestDTO.getUsername());
        if(compteRequestDTO.getEmail()!=null) compte.setEmail(compteRequestDTO.getEmail());
        if(compteRequestDTO.getPassword()!=null) compte.setPassword(passwordEncoder.encode(compteRequestDTO.getPassword()));
        if(compteRequestDTO.getWebsiteUrl()!=null) compte.setWebsiteUrl(compteRequestDTO.getWebsiteUrl());
        if(compteRequestDTO.getTwitterUsername()!=null) compte.setTwitterUsername(compteRequestDTO.getTwitterUsername());
        if(compteRequestDTO.getImageUrl()!=null) compte.setImageUrl(compteRequestDTO.getImageUrl());
        if(compteRequestDTO.getStatus()!=null) compte.setStatus(compteRequestDTO.getStatus());
        if(compteRequestDTO.getCompanyName()!=null) compte.setCompanyName(compteRequestDTO.getCompanyName());
        if(compteRequestDTO.getLocation()!=null) compte.setLocation(compteRequestDTO.getLocation());
        if(compteRequestDTO.getType()!=null) compte.setType(compteRequestDTO.getType());
    }
}
