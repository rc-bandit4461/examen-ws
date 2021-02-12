package enset.bdcc.webservices.compteoperationsservice.feign;
import enset.bdcc.webservices.compteoperationsservice.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service", url="http://localhost:8000")
public interface ClientRestClient {


    @GetMapping(path = "/clients/{id}")
    public Client getClientById(@PathVariable("id") Long id);

}
