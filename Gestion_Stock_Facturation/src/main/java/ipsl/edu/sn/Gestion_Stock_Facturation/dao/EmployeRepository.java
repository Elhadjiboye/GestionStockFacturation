package ipsl.edu.sn.Gestion_Stock_Facturation.dao;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.ClientEmployeVue;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Employe;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    Employe findByEmail(String email);

}
