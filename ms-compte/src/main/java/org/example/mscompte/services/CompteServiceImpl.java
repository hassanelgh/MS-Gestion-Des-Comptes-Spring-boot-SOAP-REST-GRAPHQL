package org.example.mscompte.services;

import lombok.extern.slf4j.Slf4j;
import org.example.mscompte.dto.CompteDetailResponseDTO;
import org.example.mscompte.dto.CompteRequestDTO;
import org.example.mscompte.dto.CompteResponseDTO;
import org.example.mscompte.dto.ComptesResponseDTOPage;
import org.example.mscompte.entities.Compte;
import org.example.mscompte.exceptions.CompteNotFoundException;
import org.example.mscompte.exceptions.FollowingException;
import org.example.mscompte.exceptions.UnfollowingException;
import org.example.mscompte.mappers.CompteMapper;
import org.example.mscompte.repositories.CompteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public ComptesResponseDTOPage getAllComptesByName(String name, int pageCurrent, int size) {
        Page<Compte> page =compteRepository.findByNomContains(name,PageRequest.of(pageCurrent,size));

        log.info("get comptes page "+pageCurrent+" & size "+size+" has name contains '"+name +"' successfully");
        return compteMapper.fromComptesPage(page);
    }

    @Override
    public CompteDetailResponseDTO getCompteById(String idCompte) {
        Compte compte =findCompteById(idCompte);

        log.info("get compte  '"+idCompte +"' successfully");

        CompteDetailResponseDTO compteDetailResponseDTO=compteMapper.fromCompteToCompteDetailResponseDTO(compte);
        compteDetailResponseDTO.setNbrFollowers(compteRepository.findAllByFollowingsContains(compte).size());
        compteDetailResponseDTO.setNbrFollowings(compte.getFollowings().size());

        return compteDetailResponseDTO;
    }

    @Override
    public CompteDetailResponseDTO updateCompte(CompteRequestDTO compteRequestDTO, String idCompte) {
        Compte compte =findCompteById(idCompte);

        compteMapper.fromCompteRequestDTOToCompte(compte, compteRequestDTO);

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

        // delete relashing  followings & followers  with this compte   :
        for (int i = 0; i < compte.getFollowings().size(); i++) {
            unFollowing(compte.getId(),compte.getFollowings().get(i).getId());
        }
        List<Compte> followers=compteRepository.findAllByFollowingsContains(compte);
        for (int i = 0; i < followers.size(); i++) {
            unFollowing(followers.get(i).getId(),compte.getId());

        }

        compteRepository.deleteById(idCompte);

        log.info("delete compte '"+idCompte+"' successfully");

        return idCompte;
    }

    @Override
    public ComptesResponseDTOPage getFollowers(String idCompte, int pageCurrent, int size) {
        Compte compte =findCompteById(idCompte);

        Page<Compte> page =compteRepository.findAllByFollowingsContains(compte,PageRequest.of(pageCurrent,size));

        log.info("get comptes followers of '"+idCompte+"' : page "+pageCurrent+" & size "+size+" successfully");

        return compteMapper.fromComptesPage(page);
    }

    @Override
    public ComptesResponseDTOPage getFollowings(String idCompte,int pageCourrent,int size) {
        Compte compte =findCompteById(idCompte);

        List<Compte> comptesFollowings =compte.getFollowings();

        Page<Compte> page=new PageImpl<>(comptesFollowings,PageRequest.of(pageCourrent,size),comptesFollowings.size());

        log.info("get comptes followeings  '"+idCompte+"' : page "+pageCourrent+" & size "+size+" successfully");

        return compteMapper.fromComptesPage(page);
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
