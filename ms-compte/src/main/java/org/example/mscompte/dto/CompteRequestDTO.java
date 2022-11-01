package org.example.mscompte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mscompte.enums.CompteType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor
public class CompteRequestDTO {

    private String nom;
    private String prenom;
    private String description;
    private String username;
    private String email;
    private String password;
    private String websiteUrl;
    private String twitterUsername;
    private String imageUrl;
    private String status;
    private String companyName;
    private String location;
    private CompteType type;
}
