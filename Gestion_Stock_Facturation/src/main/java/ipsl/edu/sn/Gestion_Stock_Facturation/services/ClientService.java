package ipsl.edu.sn.Gestion_Stock_Facturation.services;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Client;

import java.util.List;

public interface ClientService {
    Client addClient(Client c);
    List<Client> getAllClients();
    Client updateClient(Integer id,Client c);
    String deleteClient(Integer id);
    Client getOneClient(Integer id);



}
