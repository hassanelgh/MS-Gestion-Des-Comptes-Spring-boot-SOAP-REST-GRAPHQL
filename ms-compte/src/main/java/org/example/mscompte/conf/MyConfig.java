package org.example.mscompte.conf;


import lombok.extern.slf4j.Slf4j;
import org.example.mscompte.services.CompteService;
import org.example.mscompte.web.CompteSoap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Endpoint;

@Configuration
@Slf4j
public class MyConfig {

    @Value("${server.soap-service-url}")
    private String url;

    @Bean
    public Endpoint endpoint(CompteService compteService) {
        Endpoint endpoint = Endpoint.publish(url, new CompteSoap(compteService));
        log.info(">> Soap service started on :  "+url);
        return endpoint;
    }

}
