package ipsl.edu.sn.Gestion_Stock_Facturation.dao;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
}
