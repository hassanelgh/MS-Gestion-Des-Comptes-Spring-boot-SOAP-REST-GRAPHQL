## Test SOAP :

<div  align="center">
        <img src="images/img_39.png" alt="">    
</div>


```java
  @WebMethod()
  public List<CompteResponseDTO> getAllComptes()
```

<div  align="center">
        <img src="images/img_40.png" alt="">
</div>


```java
  @WebMethod()
  public ComptesResponseDTOPage getAllComptesByName(@WebParam(name = "name") String name,
                                                      @WebParam(name = "page") int page,
                                                      @WebParam(name = "size") int size)
```

<div  align="center">
        <img src="images/img_41.png" alt="">
</div>


```java
  @WebMethod
  public CompteDetailResponseDTO getCompteById(@WebParam(name = "idCompte") String idCompte)
  }
```

<div  align="center">
        <img src="images/img_42.png" alt="">
</div>



```java
  @WebMethod
  public CompteDetailResponseDTO updateCompte(@WebParam(name = "compteRequest") CompteRequestDTO compteRequestDTO, @WebParam(name = "idCompte") String idCompte)
```

<div  align="center">
        <img src="images/img_43.png" alt="">
</div>


```java
  @WebMethod
  public CompteDetailResponseDTO saveCompte(@WebParam(name = "compteRequest") CompteRequestDTO compteRequestDTO)
```

<div  align="center">
        <img src="images/img_44.png" alt="">
</div>

```java
  @WebMethod
  public String deleteCompte(@WebParam(name = "idCompte") String idCompte)
```

<div  align="center">
        <img src="images/img_45.png" alt="">
</div>


```java
  @WebMethod
  public ComptesResponseDTOPage getFollowers(@WebParam(name = "idCompte") String idCompte,
                                              @WebParam(name = "page") int page,
                                              @WebParam(name = "size") int size)
```
<div  align="center">
        <img src="images/img_46.png" alt="">
</div>


```java
  @WebMethod()
  public ComptesResponseDTOPage getFollowings(@WebParam(name = "idCompte") String idCompte,
                                              @WebParam(name = "page") int page,
                                              @WebParam(name = "size") int size)
```


<div  align="center">
        <img src="images/img_47.png" alt="">
</div>


```java
  @WebMethod()
  public String following(@WebParam(name = "idCompte") String idCompte , @WebParam(name = "following") FollowingRequest following)
```


<div  align="center">
        <img src="images/img_48.png" alt="">
</div>

<div  align="center">
        <img src="images/img_49.png" alt="">
</div>

<div  align="center">
        <img src="images/img_50.png" alt="">
</div>

<div  align="center">
        <img src="images/img_51.png" alt="">
</div>
