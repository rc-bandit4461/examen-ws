package enset.bdcc.webservices.compteoperationsservice.web;

import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import enset.bdcc.webservices.compteoperationsservice.entities.Operation;
import enset.bdcc.webservices.compteoperationsservice.model.CompteOperation;
import enset.bdcc.webservices.compteoperationsservice.model.CompteVirement;
import enset.bdcc.webservices.compteoperationsservice.service.OperationsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@CrossOrigin("*")
public class OperationsRestController {
    @Autowired
    private OperationsService operationsService;

    @Transactional
    @PostMapping("/addCompte")
    public Compte addCompte(@RequestBody NewAccount newAccount) {
        return operationsService.addCompte(newAccount.getClientId(), newAccount.getTypeCompte());
    }

    @Transactional
    @PostMapping("/versement")
    public Operation versment(@RequestBody CompteOperation compteOperation) {
        System.out.println(compteOperation);
        return operationsService.versment(compteOperation);
    }

    @Transactional
    @PostMapping("/retrait")
    public Operation retrait(@RequestBody CompteOperation compteOperation) {
        return operationsService.retrait(compteOperation);
    }

    @Transactional
    @PostMapping("/virement")
    public void virement(@RequestBody CompteVirement virement) {
        operationsService.virement(virement);
    }


    @GetMapping("/comptes/{id}/operations/{page}/{size}")
    public Page<Operation> getOperationsPaginated(@PathVariable Long compteId, @PathVariable int page, @PathVariable int size) {
        return operationsService.getOperationsPaginated(compteId, page, size);
    }

    @GetMapping("/clients/{id}/compte")
    public Compte getCompteByClient(@PathVariable Long clientId) {
        return operationsService.getCompteByClient(clientId);
    }

    @Transactional
    @PostMapping("/comptes/{id}/activate")
    public void activateCompte(@PathVariable Long compteId) {
        operationsService.activateCompte(compteId);
    }

    @Transactional
    @PostMapping("/compte/{id}/suspend")
    public void suspendCompte(@PathVariable Long compteId) {
        operationsService.activateCompte(compteId);
    }


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class NewAccount {
    private Long clientId;
    private String typeCompte;
}
