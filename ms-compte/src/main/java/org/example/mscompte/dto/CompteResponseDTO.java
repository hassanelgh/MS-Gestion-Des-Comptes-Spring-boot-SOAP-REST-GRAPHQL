package org.example.mscompte.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.mscompte.enums.CompteType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor
public class CompteResponseDTO {
    private String id;
    private String nom;
    private String description;
    private String username;
    private String location;
    @Enumerated(EnumType.STRING)
    private CompteType type;
}
