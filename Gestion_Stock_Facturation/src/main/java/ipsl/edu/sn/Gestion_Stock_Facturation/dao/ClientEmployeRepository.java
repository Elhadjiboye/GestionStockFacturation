package ipsl.edu.sn.Gestion_Stock_Facturation.dao;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.ClientEmployeVue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEmployeRepository extends JpaRepository<ClientEmployeVue, Integer> {
    ClientEmployeVue findByEmail(String email);
}
