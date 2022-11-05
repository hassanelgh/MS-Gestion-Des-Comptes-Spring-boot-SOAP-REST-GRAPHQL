package org.example.mscompte.web;

import org.example.mscompte.dto.*;
import org.example.mscompte.exceptions.CompteNotFoundException;
import org.example.mscompte.exceptions.FollowingException;
import org.example.mscompte.exceptions.UnfollowingException;
import org.example.mscompte.services.CompteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restapi")
public class CompteRestController {

    private CompteService compteService;

    public CompteRestController(CompteService compteService) {
        this.compteService = compteService;
    }

    @GetMapping("/comptes")
    public List<CompteResponseDTO> getAllComptesRest(){
        return compteService.getAllComptes();
    }

    @GetMapping("/comptes/search")
    public ComptesResponseDTOPage getAllComptesByNameRest(@RequestParam(name = "nom",defaultValue = "") String name,
                                                          @RequestParam(name = "page",defaultValue = "0") int page,
                                                          @RequestParam(name = "size",defaultValue = "5") int size
                                                           ){
        return compteService.getAllComptesByName(name,page,size);
    }

    @GetMapping("/comptes/{idCompte}")
    public CompteDetailResponseDTO getCompteByIdRest(@PathVariable(name = "idCompte") String idCompte){
        return compteService.getCompteById(idCompte);
    }

    @PutMapping("/comptes/{idCompte}")
    public CompteDetailResponseDTO updateCompteRest(@RequestBody CompteRequestDTO compteRequestDTO,@PathVariable(name = "idCompte") String idCompte){
        return compteService.updateCompte(compteRequestDTO,idCompte);
    }

    @PostMapping("/comptes")
    public CompteDetailResponseDTO saveCompteRest(@RequestBody CompteRequestDTO compteRequestDTO){
        return compteService.saveCompte(compteRequestDTO);
    }

    @DeleteMapping("/comptes/{idCompte}")
    public String deleteCompteRest(@PathVariable(name = "idCompte") String idCompte){
        return compteService.deleteCompte(idCompte);
    }

    @GetMapping("/comptes/{idCompte}/followers")
    public ComptesResponseDTOPage getFollowersRest(@PathVariable(name = "idCompte") String idCompte,
                                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                                    @RequestParam(name = "size",defaultValue = "5") int size){
        return compteService.getFollowers(idCompte,page,size);
    }

    @GetMapping("/comptes/{idCompte}/followings")
    public ComptesResponseDTOPage getFollowingsRest(@PathVariable(name = "idCompte") String idCompte,
                                                     @RequestParam(name = "page",defaultValue = "0") int page,
                                                     @RequestParam(name = "size",defaultValue = "5") int size){
        return compteService.getFollowings(idCompte,page,size);
    }

    @PutMapping("/comptes/{idCompte}/followings")
    public String followingRest(@PathVariable(name = "idCompte") String idCompte , @RequestBody FollowingRequest following){
        if(following.isFollowing())
            return compteService.addFollowing(idCompte,following.getIdCompteFollowing());
        return compteService.unFollowing(idCompte, following.getIdCompteFollowing());
    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionsHandler(Exception e){
        if(e instanceof CompteNotFoundException ){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        else if(e instanceof FollowingException || e instanceof UnfollowingException){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
