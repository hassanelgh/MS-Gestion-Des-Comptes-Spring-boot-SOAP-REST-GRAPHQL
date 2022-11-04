## Test REST API :

- CompteRestController : [:point_right:](./src/main/java/org/example/mscompte/web/CompteRestController.java)


<div  align="center">
        <img src="images/img_10.png" alt="">    
</div>

```java
@GetMapping("/comptes")
public List<CompteResponseDTO> getAllComptesRest()
```


<div  align="center">
        <img src="images/img_11.png" alt="">    
</div>




```java
@GetMapping("/comptes/search")
public ComptesResponseDTOPage getAllComptesByNameRest(@RequestParam(name = "nom",defaultValue = "") String name,
                                                          @RequestParam(name = "page",defaultValue = "0") int page,
                                                          @RequestParam(name = "size",defaultValue = "5") int size
                                                           )
```


<div  align="center">
        <img src="images/img_12.png" alt="">    
 </div>





```java
@GetMapping("/comptes/{idCompte}")
public CompteDetailResponseDTO getCompteByIdRest(@PathVariable(name = "idCompte") String idCompte)
```



<div  align="center">
        <img src="images/img_13.png" alt="">    
 </div>


```java
@PostMapping("/comptes")
public CompteDetailResponseDTO saveCompteRest(@RequestBody CompteRequestDTO compteRequestDTO)
```

<div  align="center">
        <img src="images/img_14.png" alt="">    
 </div>



```java
@PutMapping("/comptes/{idCompte}")
public CompteDetailResponseDTO updateCompteRest(@RequestBody CompteRequestDTO compteRequestDTO,
                                                @PathVariable(name = "idCompte") String idCompte)
```

<div  align="center">
        <img src="images/img_15.png" alt="">    
 </div>



```java
@DeleteMapping("/comptes/{idCompte}")
public String deleteCompteRest(@PathVariable(name = "idCompte") String idCompte)
```


<div  align="center">
        <img src="images/img_16.png" alt="">    
 </div>

```java
@GetMapping("/comptes/{idCompte}/followers")
public ComptesResponseDTOPage getFollowersRest(@PathVariable(name = "idCompte") String idCompte,
                                                    @RequestParam(name = "page",defaultValue = "0") int page,
                                                    @RequestParam(name = "size",defaultValue = "5") int size)
```


<div  align="center">
        <img src="images/img_17.png" alt="">    
 </div>

```java
@GetMapping("/comptes/{idCompte}/followings")
public ComptesResponseDTOPage getFollowingsRest(@PathVariable(name = "idCompte") String idCompte,
                                                     @RequestParam(name = "page",defaultValue = "0") int page,
                                                     @RequestParam(name = "size",defaultValue = "5") int size)
```


<div  align="center">
        <img src="images/img_18.png" alt="">    
 </div>

```java
@PutMapping("/comptes/{idCompte}/followings")
public String followingRest(@PathVariable(name = "idCompte") String idCompte , @RequestBody FollowingRequest following)
```

<div  align="center">
        <img src="images/img_19.png" alt="">    
 </div>

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<String> exceptionsHandler(Exception e)
```


<div  align="center">
        <img src="images/img_20.png" alt="">    
 </div>



<div  align="center">
        <img src="images/img_21.png" alt="">    
 </div>


<div  align="center">
        <img src="images/img_22.png" alt="">    
 </div>