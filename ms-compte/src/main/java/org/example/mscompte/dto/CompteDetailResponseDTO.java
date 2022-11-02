package org.example.mscompte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mscompte.enums.CompteType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor
public class CompteDetailResponseDTO {
    private String id;
    private String nom;
    private String description;
    private String username;
    private String email;
    private String websiteUrl;
    private String twitterUsername;
    private String imageUrl;
    private String status;
    private String companyName;
    private String location;

    @Enumerated(EnumType.STRING)
    private CompteType type;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date dateCreation;

    private int nbrFollowers;
    private int nbrFollowings;

}
