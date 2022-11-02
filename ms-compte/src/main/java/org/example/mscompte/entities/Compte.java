package org.example.mscompte.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mscompte.enums.CompteType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Compte {

    @Id
    private String id;
    private String nom;
    private String description;

    @Column(unique = true)
    private String username;
    private String email;
    private String password;
    private String websiteUrl;
    private String twitterUsername;
    private String imageUrl;
    private String status;
    private String companyName;
    private String location;
    @Enumerated(EnumType.STRING)
    private CompteType type;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreation;

    @ManyToMany
    private List<Compte>  followings=new ArrayList<>();
}
