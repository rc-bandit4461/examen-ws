package enset.bdcc.webservices.compteoperationsservice.model;
import enset.bdcc.webservices.compteoperationsservice.entities.Compte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String nom;
    private String email;
    private List<Compte> comptes = new ArrayList<>();
}
