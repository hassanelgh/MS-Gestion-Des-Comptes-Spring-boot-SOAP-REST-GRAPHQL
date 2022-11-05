## Test GraphQl :


```java
@QueryMapping()
public List<CompteResponseDTO> getAllComptes()
```

<div  align="center">
        <img src="images/img_24.png" alt="">    
 </div>


```java
@QueryMapping()
public ComptesResponseDTOPage getAllComptesByName(@Argument(name = "name") String name,
                                                    @Argument(name = "page") int page,
                                                    @Argument(name = "size") int size
        )
```

<div  align="center">
        <img src="images/img_25.png" alt="">    
 </div>


```java
@QueryMapping
public CompteDetailResponseDTO getCompteById(@Argument(name = "idCompte") String idCompte)
```

<div  align="center">
        <img src="images/img_26.png" alt="">    
 </div>


```java
@MutationMapping
public CompteDetailResponseDTO updateCompte(@Argument CompteRequestDTO compteRequestDTO, 
                                            @Argument(name = "idCompte") String idCompte)
```

<div  align="center">
        <img src="images/img_27.png" alt="">    
 </div>


```java
@MutationMapping
public CompteDetailResponseDTO saveCompte(@Argument CompteRequestDTO compteRequestDTO)
```

<div  align="center">
        <img src="images/img_28.png" alt="">    
 </div>

```java
@MutationMapping
public String deleteCompte(@Argument(name = "idCompte") String idCompte)
```
<div  align="center">
        <img src="images/img_29.png" alt="">
 </div>


```java
@QueryMapping
public ComptesResponseDTOPage getFollowers(@Argument(name = "idCompte") String idCompte,
                                            @Argument(name = "page") int page,
                                            @Argument(name = "size") int size)
```

<div  align="center">
        <img src="images/img_30.png" alt="">
 </div>

```java
@QueryMapping
public ComptesResponseDTOPage getFollowings(@Argument(name = "idCompte") String idCompte,
                                            @Argument(name = "page") int page,
                                            @Argument(name = "size") int size)
```
<div  align="center">
        <img src="images/img_31.png" alt="">
 </div>


```java
@MutationMapping
public String following(@Argument(name = "idCompte") String idCompte , 
                        @Argument FollowingRequest following)
```

<div  align="center">
        <img src="images/img_32.png" alt="">    
 </div>

<div  align="center">
        <img src="images/img_34.png" alt="">
 </div>


<div  align="center">
        <img src="images/img_33.png" alt="">
 </div>


<div  align="center">
        <img src="images/img_35.png" alt="">
 </div>



- exceptions :


<div  align="center">
        <img src="images/img_36.png" alt="">
 </div>



<div  align="center">
        <img src="images/img_37.png" alt="">
 </div>


<div  align="center">
        <img src="images/img_38.png" alt="">
 </div>