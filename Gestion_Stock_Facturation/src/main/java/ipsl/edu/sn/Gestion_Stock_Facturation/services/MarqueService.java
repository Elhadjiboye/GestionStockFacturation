package ipsl.edu.sn.Gestion_Stock_Facturation.services;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Categorie;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Marque;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Produit;

import java.util.List;
import java.util.Set;

public interface MarqueService {
    Marque  creer(Marque marque);
    List <Marque> lire();
    boolean existsById(int id);
    Marque  modifier(int id ,Marque marque);
    String  supprimer(int id);
    Marque lireParId(int id);
    Marque lireParNom(String nom);
    List<Marque> rechercherParNom(String nom);
    Set<Produit> lireProduitsParId(int id);
    Set<Categorie> lireCategoriesParId(int id);
}
