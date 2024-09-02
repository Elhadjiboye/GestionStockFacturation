package ipsl.edu.sn.Gestion_Stock_Facturation.services.Implement;

import ipsl.edu.sn.Gestion_Stock_Facturation.dao.MarqueRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Categorie;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Marque;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Produit;
import ipsl.edu.sn.Gestion_Stock_Facturation.exceptions.MarqueNotFoundException;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.MarqueService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MarqueServiceImplement implements MarqueService {

    private final MarqueRepository marqueRepository;

    @Override
    public Marque creer(Marque marque) {
        return marqueRepository.save(marque);
    }

    @Override
    public List<Marque> lire() {
        return marqueRepository.findAll();
    }

    @Override
    public boolean existsById(int id) {
        return marqueRepository.existsById(id);
    }


    @Override
    public Marque modifier(int id, Marque marque) {
        marque.setId(id);
        return marqueRepository.save(marque);
    }


    @Override
    public String supprimer(int id) {
        if (!marqueRepository.existsById(id)) {
            throw new MarqueNotFoundException("Marque non trouvée avec l'id : " + id);
        }
        marqueRepository.deleteById(id);
        return "Marque supprimée avec l'id : " + id;
    }
    @Override
    public Marque lireParId(int id) {
        return marqueRepository.findById(id)
                .orElseThrow(() -> new MarqueNotFoundException("Marque non trouvée avec l'id : " + id));
    }
    @Override
    public Marque lireParNom(String nom) {
        return marqueRepository.findByNom(nom);
    }

    @Override
    public List<Marque> rechercherParNom(String nom) {
        return marqueRepository.findByNomContainingIgnoreCase(nom);
    }

    @Override
    public Set<Produit> lireProduitsParId(int id) {
        Marque marque = lireParId(id);
        return marque.getProduits();
    }
    @Override
    public Set<Categorie> lireCategoriesParId(int id) {
        Marque marque = lireParId(id);
        return marque.getProduits().stream()
                .map(Produit::getCategorie)
                .collect(Collectors.toSet());
    }
}
