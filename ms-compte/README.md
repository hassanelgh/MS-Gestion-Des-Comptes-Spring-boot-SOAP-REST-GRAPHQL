# Micro Service Compte 

### Dependencies :
    
 - spring web 
 - spring data jpa
 - lombok
 - h2 database 
 - mapstruct


### Entities :

 - Compte : [:point_right:](./src/main/java/org/example/mscompte/entities/Compte.java)
    
    <div  align="center">
        <img src="images/img.png" alt="" height="400">
    </div>

### Enums :
 
 - CompteType : [:point_right:](./src/main/java/org/example/mscompte/enums/CompteType.java)

    <div  align="center">
        <img src="images/img_1.png" alt="">    
    </div>


### DTO :

- `CompteRequestDTO` [:point_right:](./src/main/java/org/example/mscompte/dto/CompteRequestDTO.java) :

   <div  align="center">
        <img src="images/img_2.png" alt="">
    </div>

- `CompteResponseDTO` [:point_right:](./src/main/java/org/example/mscompte/dto/CompteResponseDTO.java) : les informations nécessaires des comptes 

   <div  align="center">
        <img src="images/img_3.png" alt="">
    </div>

- `CompteDetailResponseDTO` [:point_right:](./src/main/java/org/example/mscompte/dto/CompteDetailResponseDTO.java) : les informations en détail d'un compte

   <div  align="center">
        <img src="images/img_4.png" alt="">    
    </div>


### Repositories :

Par l'utilisation de JPA 

 - CompteRepository : [:point_right:](./src/main/java/org/example/mscompte/repositories/CompteRepository.java)

   <div  align="center">
        <img src="images/img_5.png" alt="">    
    </div>


### Mappers :

Par l'utilisation de mapstruct 

 - CompteMapper : [:point_right:](./src/main/java/org/example/mscompte/mappers/CompteMapper.java)

   <div  align="center">
        <img src="images/img_6.png" alt="">    
    </div>
   

