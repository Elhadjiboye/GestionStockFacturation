package ipsl.edu.sn.Gestion_Stock_Facturation.dao;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNom(String nom);
}
