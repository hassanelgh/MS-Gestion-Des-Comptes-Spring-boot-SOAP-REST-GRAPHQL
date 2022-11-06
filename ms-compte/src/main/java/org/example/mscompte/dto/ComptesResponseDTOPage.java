package org.example.mscompte.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement(name = "comptesResponsePage")
public class ComptesResponseDTOPage {

    private int page;
    private int totalePages;
    private int totaleElements;
    private int size;
    private List<CompteResponseDTO> compteResponseDTOS;
}
