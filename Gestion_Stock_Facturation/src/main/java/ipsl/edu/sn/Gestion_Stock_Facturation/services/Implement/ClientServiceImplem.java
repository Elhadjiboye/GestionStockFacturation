package ipsl.edu.sn.Gestion_Stock_Facturation.services.Implement;

import ipsl.edu.sn.Gestion_Stock_Facturation.dao.ClientRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Client;
import ipsl.edu.sn.Gestion_Stock_Facturation.exceptions.ClientNotFoundException;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImplem implements ClientService {

    public final ClientRepository clientRepository;

    @Override
    public Client addClient(Client c) {
        return clientRepository.save(c);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Integer id, Client c) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client non trouver avec l'id: "+ id);
        }
        c.setId(id);
        return clientRepository.save(c);
    }

    @Override
    public String deleteClient(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client non trouver avec l'id: "+ id);
        }
        clientRepository.deleteById(id);
        return "Client supprimée avec l'id : " + id;

    }

    @Override
    public Client getOneClient(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client n'existe pas avec l'id : " + id));

    }

}
