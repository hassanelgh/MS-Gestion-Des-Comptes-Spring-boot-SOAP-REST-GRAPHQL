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
public class CompteResponseDTO {
    private String id;
    private String nom;
    private String prenom;
    private String description;
    private String username;
    private String location;
}
