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
            for (int i = 0; i < 20; i++) {

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

            for( int i = 0; i < allComptes.size()/2; i++ ){
                CompteResponseDTO compteResponseDTO0=allComptes.get(i);
                CompteResponseDTO compteResponseDTO1=allComptes.get(allComptes.size()-i-1);

                if(Math.random()>0.25){
                    compteService.addFollowing(compteResponseDTO0.getId(), compteResponseDTO1.getId());
                }
                else if(Math.random()>0.5){
                    compteService.addFollowing(compteResponseDTO1.getId(), compteResponseDTO0.getId());
                }
                else if(Math.random()>0.75 && !compteResponseDTO0.getId().equals(compteResponseDTO.getId())){
                    compteService.addFollowing(compteResponseDTO1.getId(), compteResponseDTO.getId());
                }
                else if(!compteResponseDTO0.getId().equals(compteResponseDTO.getId())){
                    compteService.addFollowing(compteResponseDTO.getId(), compteResponseDTO1.getId());
                }
                compteResponseDTO=compteResponseDTO0;

            }
        };
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
