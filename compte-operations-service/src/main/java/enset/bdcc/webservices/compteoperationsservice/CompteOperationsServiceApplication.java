package enset.bdcc.webservices.compteoperationsservice;

import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import enset.bdcc.webservices.compteoperationsservice.entities.Operation;
import enset.bdcc.webservices.compteoperationsservice.entities.repos.CompteRepository;
import enset.bdcc.webservices.compteoperationsservice.entities.repos.OperationRepository;
import enset.bdcc.webservices.compteoperationsservice.feign.ClientRestClient;
import enset.bdcc.webservices.compteoperationsservice.model.*;
import enset.bdcc.webservices.compteoperationsservice.service.OperationsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableKafka
public class CompteOperationsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteOperationsServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(OperationsService operationsService, CompteRepository compteRepository, OperationRepository operationRepository, ClientRestClient clientRestClient, RepositoryRestConfiguration restConfiguration) {
        restConfiguration.exposeIdsFor(Compte.class);
        restConfiguration.exposeIdsFor(Operation.class);
        return args -> {
//            Client client1 = clientRestClient.getClientById(1L);
//            operationsService.addCompte(client1.getId(), CompteTypes.COURANT);
//            operationsService.addCompte(client1.getId(), CompteTypes.EPARGNE);
//            List<Compte> comptes = compteRepository.getCompteByIdClient(client1.getId());
//            System.out.println("COMPTES AFFICHAGE");
//            comptes.forEach(compte -> System.out.println(compte.getId()));
//            CompteOperation debitOperation = new CompteOperation();
//            debitOperation.setCompteId(comptes.get(0).getId());
//            debitOperation.setDate(LocalDateTime.now());
//            debitOperation.setMontant(5000.);
//            debitOperation.setType(OperationTypes.DEBIT);
//            operationsService.versment(debitOperation);
//            CompteOperation debitOperation2 = new CompteOperation();
//            debitOperation2.setCompteId(comptes.get(1).getId());
//            debitOperation2.setDate(LocalDateTime.now());
//            debitOperation2.setMontant(5000.);
//            debitOperation2.setType(OperationTypes.DEBIT);
//            operationsService.versment(debitOperation2);
//            CompteOperation creditOperation = new CompteOperation();
//            creditOperation.setCompteId(comptes.get(1).getId());
//            creditOperation.setDate(LocalDateTime.now());
//            creditOperation.setMontant(1500.);
//            creditOperation.setType(OperationTypes.CREDIT);
//            operationsService.retrait(creditOperation);
//            CompteVirement compteVirement = new CompteVirement();
//            compteVirement.setCompteIdSource(comptes.get(0).getId());
//            compteVirement.setCompteIdDest(comptes.get(1).getId());
//            compteVirement.setDate(LocalDateTime.now());
//            compteVirement.setMontant(1500.);
//            operationsService.virement(compteVirement);
        };
    }

}
