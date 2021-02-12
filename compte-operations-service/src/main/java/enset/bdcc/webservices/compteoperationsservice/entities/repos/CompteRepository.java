package enset.bdcc.webservices.compteoperationsservice.entities.repos;

import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CompteRepository extends JpaRepository<Compte,Long> {
    public List<Compte> getCompteByIdClient(Long idClient);

}
