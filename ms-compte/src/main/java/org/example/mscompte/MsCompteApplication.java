package org.example.mscompte;

import com.github.javafaker.Faker;
import org.example.mscompte.dto.CompteRequestDTO;
import org.example.mscompte.dto.CompteResponseDTO;
import org.example.mscompte.enums.CompteType;
import org.example.mscompte.services.CompteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Streamable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class MsCompteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCompteApplication.class, args);
    }



    @Bean
    CommandLineRunner commandLineRunner(CompteService compteService){
        return args -> {
            Faker faker = new Faker();

            // creer des comptes :
            for (int i = 0; i < 4; i++) {

                String fullName = faker.name().fullName();
                String address = faker.address().fullAddress();
                CompteRequestDTO compteRequestDto=CompteRequestDTO.builder()
                        .nom(fullName)
                        .username(fullName.replace(" ","-")+"-username")
                        .companyName(faker.company().name())
                        .description(" my name is " + fullName +" my " +faker.date().birthday().toString() + " I'm from "+address +" ..." )
                        .email(fullName.replace(" ", "")+"@gmail.com")
                        .imageUrl("/images/"+fullName.replace(" ", "-"))
                        .location(address)
                        .password(fullName.replace(" ", ""))
                        .status(Math.random()>0.5 ? "work" :"sleep")
                        .twitterUsername(fullName.replace(" ","-"))
                        .type(Math.random()>0.5 ? CompteType.PRIVATE :CompteType.PUBLIC)
                        .websiteUrl("https://website/"+fullName.replace(" ", "-"))
                        .build();

                compteService.saveCompte(compteRequestDto);
            }


            List<CompteResponseDTO> allComptes = compteService.getAllComptes();
            CompteResponseDTO compteResponseDTO=allComptes.get(0);
            CompteResponseDTO compteResponseDTO1=allComptes.get(1);
            CompteResponseDTO compteResponseDTO2=allComptes.get(2);
            CompteResponseDTO compteResponseDTO3=allComptes.get(3);

            compteService.addFollowing(compteResponseDTO.getId(), compteResponseDTO1.getId());
            compteService.addFollowing(compteResponseDTO.getId(), compteResponseDTO2.getId());
            compteService.addFollowing(compteResponseDTO1.getId(), compteResponseDTO.getId());
            compteService.addFollowing(compteResponseDTO1.getId(), compteResponseDTO2.getId());
            compteService.addFollowing(compteResponseDTO2.getId(), compteResponseDTO3.getId());

        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
