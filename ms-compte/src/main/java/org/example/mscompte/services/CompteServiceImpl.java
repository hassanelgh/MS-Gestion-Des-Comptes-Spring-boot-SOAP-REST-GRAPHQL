package org.example.mscompte.services;

import lombok.extern.slf4j.Slf4j;
import org.example.mscompte.dto.CompteDetailResponseDTO;
import org.example.mscompte.dto.CompteRequestDTO;
import org.example.mscompte.dto.CompteResponseDTO;
import org.example.mscompte.entities.Compte;
import org.example.mscompte.enums.CompteType;
import org.example.mscompte.exceptions.CompteNotFoundException;
import org.example.mscompte.exceptions.FollowingException;
import org.example.mscompte.exceptions.UnfollowingException;
import org.example.mscompte.mappers.CompteMapper;
import org.example.mscompte.repositories.CompteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class CompteServiceImpl implements CompteService {

    private CompteRepository compteRepository;
    private CompteMapper compteMapper;
    private PasswordEncoder passwordEncoder;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper,PasswordEncoder passwordEncoder) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public List<CompteResponseDTO> getAllComptes() {
        List<Compte> comptes=compteRepository.findAll();
        log.info("get all comptes public successfully");
        return tranformListCompteToListCompteResponseDTOs(comptes);
    }

    @Override
    public List<CompteResponseDTO> getAllComptesByName(String name) {
        List<Compte> comptes =compteRepository.findByNomContainsAndType(name,CompteType.PUBLIC);
        log.info("get all comptes publoc has name contains '"+name +"' successfully");
        return tranformListCompteToListCompteResponseDTOs(comptes);
    }

    @Override
    public CompteDetailResponseDTO getCompteById(String idCompte) {
        Compte compte =findCompteById(idCompte);
        log.info("get compte  '"+idCompte +"' successfully");

        CompteDetailResponseDTO compteDetailResponseDTO=compteMapper.fromCompteToCompteDetailResponseDTO(compte);
        compteDetailResponseDTO.setNbrFollowers(getFollowers(compte.getId()).size());
        compteDetailResponseDTO.setNbrFollowings(compte.getFollowings().size());
        return compteDetailResponseDTO;
    }

    @Override
    public CompteDetailResponseDTO updateCompte(CompteRequestDTO compteRequestDTO, String idCompte) {
        Compte compte =findCompteById(idCompte);

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

        Compte compteSave=compteRepository.save(compte);

        log.info("update compte  '"+idCompte +"' successfully");

        return compteMapper.fromCompteToCompteDetailResponseDTO(compteSave);
    }

    @Override
    public CompteDetailResponseDTO saveCompte(CompteRequestDTO compteRequestDTO) {
        Compte compte=compteMapper.fromCompteRequestDTO(compteRequestDTO);

        compte.setDateCreation(new Date());
        compte.setId(UUID.randomUUID().toString());
        compte.setPassword(passwordEncoder.encode(compteRequestDTO.getPassword()));

        Compte compteSave=compteRepository.save(compte);


        log.info("save compte '"+compteRequestDTO.getUsername()+"' successfully");
        CompteDetailResponseDTO compteDetailResponseDTO=compteMapper.fromCompteToCompteDetailResponseDTO(compteSave);
        compteDetailResponseDTO.setNbrFollowers(0);
        compteDetailResponseDTO.setNbrFollowings(0);
        return compteMapper.fromCompteToCompteDetailResponseDTO(compteSave);
    }

    @Override
    public String deleteCompte(String idCompte) {
        Compte compte=findCompteById(idCompte);

        compteRepository.deleteById(idCompte);

        log.info("delete compte '"+idCompte+"' successfully");
        return idCompte;
    }

    @Override
    public List<CompteResponseDTO> getFollowers(String idCompte) {
        Compte compte =findCompteById(idCompte);

        List<Compte> comptesFollowers =compteRepository.findAllByFollowingsContains(compte);
        log.info("get all comptes followers of '"+idCompte+"' successfully");
        return tranformListCompteToListCompteResponseDTOs(comptesFollowers);
    }

    @Override
    public List<CompteResponseDTO> getFollowings(String idCompte) {
        Compte compte =findCompteById(idCompte);

        List<Compte> comptesFollowings =compte.getFollowings();

        log.info("get all comptes followings by '"+idCompte+"' successfully");

        return tranformListCompteToListCompteResponseDTOs(comptesFollowings);
    }

    @Override
    public String unFollowing(String idCompte , String idCompteFollowing) {
        if(idCompte.equals(idCompteFollowing)) throw new UnfollowingException("you can't unfollowing your self'");
        Compte compte=findCompteById(idCompte);
        Compte compteFollowing=findCompteById(idCompteFollowing);
        compte.getFollowings().remove(compteFollowing);

        Compte compteSave =compteRepository.save(compte);

        log.info("compte '"+idCompte+"' unfollowing '"+idCompteFollowing+"' successfully");

        return idCompteFollowing;
    }

    @Override
    public String addFollowing(String idCompte ,String idCompteFollowing) {
        if(idCompte.equals(idCompteFollowing)) throw new FollowingException("you can't following your self'");
        Compte compte=findCompteById(idCompte);
        Compte compteFollowing=findCompteById(idCompteFollowing);

        if(!compte.getFollowings().contains(compteFollowing))
            compte.getFollowings().add(compteFollowing);

        Compte compteSave =compteRepository.save(compte);

        log.info("compte '"+idCompte+"' following '"+idCompteFollowing+"' successfully");

        return idCompteFollowing;
    }


    private Compte findCompteById(String idCompte){
        return compteRepository.findById(idCompte).orElseThrow(() -> new CompteNotFoundException("Compte has id "+idCompte+" not found "));
    }

    private List<CompteResponseDTO> tranformListCompteToListCompteResponseDTOs(List<Compte> comptes){
        return comptes.stream().map(compte -> compteMapper.fromCompteToCompteResponseDTO(compte)).collect(Collectors.toList());
    }

}
