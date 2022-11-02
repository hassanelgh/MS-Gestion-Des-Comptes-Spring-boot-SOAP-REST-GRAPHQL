package org.example.mscompte.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mscompte.enums.CompteType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CompteRequestDTO {

    private String nom;
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

    @Temporal(TemporalType.DATE)
    @Enumerated(EnumType.STRING)
    private CompteType type;
}
