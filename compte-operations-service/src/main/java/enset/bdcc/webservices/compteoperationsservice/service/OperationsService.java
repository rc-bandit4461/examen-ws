package enset.bdcc.webservices.compteoperationsservice.service;

import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import enset.bdcc.webservices.compteoperationsservice.entities.Operation;
import enset.bdcc.webservices.compteoperationsservice.model.Client;
import enset.bdcc.webservices.compteoperationsservice.model.CompteOperation;
import enset.bdcc.webservices.compteoperationsservice.model.CompteVirement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OperationsService {
    Compte addCompte(Long clientId, String typeCompte);

    Operation versment(CompteOperation compteOperation);

    Operation retrait(CompteOperation compteOperation);

    Page<Operation> getOperationsPaginated(Long compteId, int page, int size);

    Client getClientComptes(Long clientId);

    List<Compte> getComptesByClient(Long clientId);

    void activateCompte(Long compteId);

    void suspendCompte(Long compteId);

    List<Operation> virement(CompteVirement compteVirement);
}
