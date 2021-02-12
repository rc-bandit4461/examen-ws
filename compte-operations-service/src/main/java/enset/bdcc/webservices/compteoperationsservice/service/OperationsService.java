package enset.bdcc.webservices.compteoperationsservice.service;

import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import enset.bdcc.webservices.compteoperationsservice.entities.Operation;
import enset.bdcc.webservices.compteoperationsservice.model.CompteOperation;
import enset.bdcc.webservices.compteoperationsservice.model.CompteVirement;
import org.springframework.data.domain.Page;

public interface OperationsService {
    public Compte addCompte(Long clientId, String typeCompte);

    public Operation versment(CompteOperation compteOperation);

    public Operation retrait(CompteOperation compteOperation);

    public Page<Operation> getOperationsPaginated(Long compteId, int page, int size);

    public Compte getCompteByClient(Long clientId);

    public void activateCompte(Long compteId);

    public void suspendCompte(Long compteId);

    public void virement(CompteVirement compteVirement);
}
