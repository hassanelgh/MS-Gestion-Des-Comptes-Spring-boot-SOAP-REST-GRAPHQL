package org.example.mscompte.web;


import org.example.mscompte.dto.*;
import org.example.mscompte.exceptions.CompteNotFoundException;
import org.example.mscompte.exceptions.FollowingException;
import org.example.mscompte.exceptions.UnfollowingException;
import org.example.mscompte.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompteGraphqlController {

    @Autowired
    private CompteService compteService;


    @QueryMapping()
    public List<CompteResponseDTO> getAllComptes(){
        return compteService.getAllComptes();
    }

    @QueryMapping()
    public ComptesResponseDTOPage getAllComptesByName(@Argument(name = "name") String name,
                                                          @Argument(name = "page") int page,
                                                          @Argument(name = "size") int size
    ){
        return compteService.getAllComptesByName(name,page,size);
    }

    @QueryMapping
    public CompteDetailResponseDTO getCompteById(@Argument(name = "idCompte") String idCompte){
        return compteService.getCompteById(idCompte);
    }

    @MutationMapping
    public CompteDetailResponseDTO updateCompte(@Argument CompteRequestDTO compteRequestDTO, @Argument(name = "idCompte") String idCompte){
        return compteService.updateCompte(compteRequestDTO,idCompte);
    }

    @MutationMapping
    public CompteDetailResponseDTO saveCompte(@Argument CompteRequestDTO compteRequestDTO){
        return compteService.saveCompte(compteRequestDTO);
    }

    @MutationMapping
    public String deleteCompte(@Argument(name = "idCompte") String idCompte){
        return compteService.deleteCompte(idCompte);
    }

    @QueryMapping
    public ComptesResponseDTOPage getFollowers(@Argument(name = "idCompte") String idCompte,
                                                   @Argument(name = "page") int page,
                                                   @Argument(name = "size") int size){
        return compteService.getFollowers(idCompte,page,size);
    }

    @QueryMapping
    public ComptesResponseDTOPage getFollowings(@Argument(name = "idCompte") String idCompte,
                                                    @Argument(name = "page") int page,
                                                    @Argument(name = "size") int size){
        return compteService.getFollowings(idCompte,page,size);
    }

    @MutationMapping
    public String following(@Argument(name = "idCompte") String idCompte , @Argument FollowingRequest following){
        if(following.isFollowing())
            return compteService.addFollowing(idCompte,following.getIdCompteFollowing());
        return compteService.unFollowing(idCompte, following.getIdCompteFollowing());
    }





}
