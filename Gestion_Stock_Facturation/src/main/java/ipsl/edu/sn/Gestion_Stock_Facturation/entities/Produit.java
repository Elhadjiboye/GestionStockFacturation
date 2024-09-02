package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Produit  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int annee_model;

    @Column(nullable = false)
    private int prix_depart;

    @Column(nullable = false, length = 255)
    private String nom;

    @ManyToOne
    @JoinColumn(nullable = false, name = "categorie_id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(nullable = false, name = "marque_id")
    private Marque marque;

    @OneToMany(mappedBy = "produit")
    private Set<Stock> stocks;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private Set<ArticleCommande> articlesCommande;
    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;
}
