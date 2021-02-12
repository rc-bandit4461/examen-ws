package enset.bdcc.webservices.demo;

import enset.bdcc.webservices.demo.entities.Client;
import enset.bdcc.webservices.demo.repos.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }
      @Bean
    public CommandLineRunner start(ClientRepository clientRepository, RepositoryRestConfiguration restConfiguration){
	    restConfiguration.exposeIdsFor(Client.class);
	    return args -> {
	      clientRepository.save(new Client(null,"Aymane","ayman.boubleh@gmail.com"));
	      clientRepository.save(new Client(null,"hamza","hamza@gmail.com"));
	      clientRepository.save(new Client(null,"ahmed","ahmed@gmail.com"));
        };
    }

}
