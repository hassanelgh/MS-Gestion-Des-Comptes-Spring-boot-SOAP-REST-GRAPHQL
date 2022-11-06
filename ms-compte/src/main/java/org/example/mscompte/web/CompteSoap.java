package org.example.mscompte.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebFault;
import org.example.mscompte.dto.*;
import org.example.mscompte.exceptions.CompteNotFoundException;
import org.example.mscompte.exceptions.FollowingException;
import org.example.mscompte.exceptions.UnfollowingException;
import org.example.mscompte.services.CompteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Component
@WebService(name = "CompteWS",serviceName = "CompteService")
public class CompteSoap {
    private CompteService compteService;

    public CompteSoap(CompteService compteService) {
        this.compteService = compteService;
    }


    @WebMethod()
    public List<CompteResponseDTO> getAllComptes(){
        return compteService.getAllComptes();
    }

    @WebMethod()
    public ComptesResponseDTOPage getAllComptesByName(@WebParam(name = "name") String name,
                                                      @WebParam(name = "page") int page,
                                                      @WebParam(name = "size") int size
    ){
        return compteService.getAllComptesByName(name,page,size);
    }

    @WebMethod
    public CompteDetailResponseDTO getCompteById(@WebParam(name = "idCompte") String idCompte){
        return compteService.getCompteById(idCompte);
    }

    @WebMethod
    public CompteDetailResponseDTO updateCompte(@WebParam(name = "compteRequest") CompteRequestDTO compteRequestDTO, @WebParam(name = "idCompte") String idCompte){
        return compteService.updateCompte(compteRequestDTO,idCompte);
    }

    @WebMethod
    public CompteDetailResponseDTO saveCompte(@WebParam(name = "compteRequest") CompteRequestDTO compteRequestDTO){
        return compteService.saveCompte(compteRequestDTO);
    }

    @WebMethod
    public String deleteCompte(@WebParam(name = "idCompte") String idCompte){
        return compteService.deleteCompte(idCompte);
    }

    @WebMethod
    public ComptesResponseDTOPage getFollowers(@WebParam(name = "idCompte") String idCompte,
                                               @WebParam(name = "page") int page,
                                               @WebParam(name = "size") int size){
        return compteService.getFollowers(idCompte,page,size);
    }

    @WebMethod()
    public ComptesResponseDTOPage getFollowings(@WebParam(name = "idCompte") String idCompte,
                                                @WebParam(name = "page") int page,
                                                @WebParam(name = "size") int size){
        return compteService.getFollowings(idCompte,page,size);
    }

    @WebMethod()
    public String following(@WebParam(name = "idCompte") String idCompte , @WebParam(name = "following") FollowingRequest following){
        if(following.isFollowing())
            return compteService.addFollowing(idCompte,following.getIdCompteFollowing());
        return compteService.unFollowing(idCompte, following.getIdCompteFollowing());
    }

}
