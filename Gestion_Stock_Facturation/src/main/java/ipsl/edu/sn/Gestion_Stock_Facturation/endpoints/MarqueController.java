package ipsl.edu.sn.Gestion_Stock_Facturation.endpoints;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Categorie;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Marque;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Produit;
import ipsl.edu.sn.Gestion_Stock_Facturation.exceptions.MarqueNotFoundException;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.MarqueService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/marques")
@AllArgsConstructor
public class MarqueController {

    private final MarqueService marqueService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Marque marque) {
        if(marque.getNom()==null) {
            return ResponseEntity.status(451).build();
        }
        Marque marque1=marqueService.lireParId(marque.getId());
        if(marque1!=null) {
            return ResponseEntity.status(452).build();
        }
        Marque marque2=marqueService.lireParNom(marque.getNom());
        if(marque2==null) {
            return ResponseEntity.status(453).build();
        }

        Marque createdMarque = marqueService.creer(marque);
        return ResponseEntity.status(201).body(createdMarque);

    }

    @GetMapping
    public ResponseEntity<List<Marque>> read() {
        List<Marque> marques = marqueService.lire();
        if(marques.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(marques);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Marque>> searchByName(@RequestParam String nom) {
        List<Marque> marques = marqueService.rechercherParNom(nom);
        if(marques.isEmpty()) {
            return ResponseEntity.status(415).build();
        }
        return ResponseEntity.ok(marques);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marque> getById(@PathVariable int id) {
        try {
            Marque marque = marqueService.lireParId(id);
            return ResponseEntity.ok(marque);
        } catch (MarqueNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}/produits")
    public ResponseEntity<Set<Produit>> getProduitsByMarqueId(@PathVariable int id) {
        try {
            Set<Produit> produits = marqueService.lireProduitsParId(id);
            return ResponseEntity.ok(produits);
        } catch (MarqueNotFoundException ex) {
            return ResponseEntity.status(418).body(null);
        }
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<Set<Categorie>> getCategoriesByMarqueId(@PathVariable int id) {
        try {
            Set<Categorie> categories = marqueService.lireCategoriesParId(id);
            return ResponseEntity.ok(categories);
        } catch (MarqueNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marque> update(@PathVariable int id, @Valid @RequestBody Marque marque) {
        if (!marqueService.existsById(id)) {
            return ResponseEntity.status(400).build();
        }

        // Si la marque existe, procéder à la mise à jour
        Marque updatedMarque = marqueService.modifier(id, marque);
        return ResponseEntity.ok(updatedMarque);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            String message = marqueService.supprimer(id);
            return ResponseEntity.ok(message);
        } catch (MarqueNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
