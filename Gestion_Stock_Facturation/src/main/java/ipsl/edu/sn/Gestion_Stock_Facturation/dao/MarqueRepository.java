package ipsl.edu.sn.Gestion_Stock_Facturation.dao;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Integer> {
    // Déclaration de la méthode personnalisée pour rechercher par nom
    List<Marque> findByNomContainingIgnoreCase(String nom);
    Marque findByNom(String nom);
}
