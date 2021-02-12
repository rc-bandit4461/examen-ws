package enset.bdcc.webservices.compteoperationsservice.service;

import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import enset.bdcc.webservices.compteoperationsservice.entities.Operation;
import enset.bdcc.webservices.compteoperationsservice.entities.repos.CompteRepository;
import enset.bdcc.webservices.compteoperationsservice.entities.repos.OperationRepository;
import enset.bdcc.webservices.compteoperationsservice.feign.ClientRestClient;
import enset.bdcc.webservices.compteoperationsservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OperationsServiceImpl implements OperationsService {
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    ClientRestClient clientRestClient;

    @Override
    public Compte addCompte(Long clientId, String typeCompte) {
        Compte compte = new Compte();
        compte.setEtat(CompteEtats.ACTIVE);
        compte.setSolde(0d);
        compte.setType(typeCompte);
        compte.setDateCreation(LocalDateTime.now());
        compte.setIdClient(clientId);
        compteRepository.save(compte);
        return compte;
    }

    @Override
    public Operation versment(CompteOperation versementOperation) {
        Operation operation = new Operation();
        operation.setMontant(versementOperation.getMontant());
        operation.setType(OperationTypes.DEBIT);
        operationRepository.save(operation);
        Compte compte = compteRepository.getOne(versementOperation.getCompteId());
        operation.setCompte(compte);
        System.out.println(compte.getSolde());
        System.out.println("operation Montation-->" + operation.getMontant());
        compte.setSolde(versementOperation.getMontant() + compte.getSolde());
        if (versementOperation.getDate() != null)
            operation.setDate(versementOperation.getDate());
        else operation.setDate(LocalDateTime.now());
        compteRepository.save(compte);
        return operation;
    }

    @Override
    public Operation retrait(CompteOperation retraitOperation) {
        Operation operation = new Operation();
        operation.setMontant(retraitOperation.getMontant());
        operation.setType(OperationTypes.CREDIT);
        operationRepository.save(operation);
        Compte compte = compteRepository.getOne(retraitOperation.getCompteId());
        operation.setCompte(compte);
        compte.setSolde(compte.getSolde() - retraitOperation.getMontant());
        if (retraitOperation.getDate() != null)
            operation.setDate(retraitOperation.getDate());
        else operation.setDate(LocalDateTime.now());
        compteRepository.save(compte);
        return operation;
    }



    @Override
    public List<Operation> virement(CompteVirement virement) {
        Compte compteSource = compteRepository.getOne(virement.getCompteIdSource());
        Compte compteDest = compteRepository.getOne(virement.getCompteIdDest());
        if (virement.getDate() == null) {
            virement.setDate(LocalDateTime.now());
        }
        Operation sourceOperation = new Operation(null, virement.getDate(), virement.getMontant(), OperationTypes.VIREMENT_CREDIT, compteSource);
        Operation destOperation = new Operation(null, virement.getDate(), virement.getMontant(), OperationTypes.VIREMENT_DEBIT, compteDest);
        compteSource.setSolde(compteSource.getSolde() - virement.getMontant());
        compteDest.setSolde(compteDest.getSolde() + virement.getMontant());
        List<Operation> operationList = new ArrayList<>();
        operationList.add(sourceOperation);
        operationList.add(destOperation);
        List<Compte> compteList = new ArrayList<>();
        compteList.add(compteDest);
        compteList.add(compteSource);
        compteRepository.saveAll(compteList);
        operationRepository.saveAll(operationList);
        return operationList;
    }
        @Override
    public void activateCompte(Long compteId) {
        Compte compte = compteRepository.getOne(compteId);
        compte.setEtat(CompteEtats.ACTIVE);
        compteRepository.save(compte);
    }

    @Override
    public void suspendCompte(Long compteId) {
        Compte compte = compteRepository.getOne(compteId);
        compte.setEtat(CompteEtats.SUSPENDED);
        compteRepository.save(compte);
    }
        @Override
    public Page<Operation> getOperationsPaginated(Long compteId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return operationRepository.getOperationsByCompteId(compteId, pageable);
    }

    @Override
    public Client getClientComptes(Long clientId) {
        Client client = clientRestClient.getClientById(clientId);
        client.setComptes(compteRepository.getCompteByIdClient(clientId));

        return client;
    }

    @Override
    public List<Compte> getComptesByClient(Long clientId) {
        return compteRepository.getCompteByIdClient(clientId);
    }

}
