package enset.bdcc.webservices.compteoperationsservice;

import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import enset.bdcc.webservices.compteoperationsservice.entities.Operation;
import enset.bdcc.webservices.compteoperationsservice.service.OperationsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@EnableFeignClients

public class CompteOperationsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteOperationsServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(OperationsService operationsService, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Compte.class);
        restConfiguration.exposeIdsFor(Operation.class);
        return args -> {

        };
    }

}
