package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Magasin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255)
    private String adresse;
    @Column(length = 255)
    private String code_zip;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String etat;
    @Column(nullable = false, length = 255)
    private String nom;
    @Column(length = 255)
    private String telephone;
    @Column(length = 255)
    private String ville;

    @OneToMany(mappedBy = "magasin")
    private Set<Employe> employes;

    @OneToMany(mappedBy = "magasin")
    private Set<Stock> stocks;

    @OneToMany(mappedBy = "magasin", cascade = CascadeType.ALL)
    private Set<Commande> commandes;

    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;
}
