package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class ArticleCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int prix_depart;
    @Column(nullable = false)
    private int quantite;
    @Column(nullable = false)
    private int remise_decimal;

    @ManyToOne
    @JoinColumn(nullable = false, name = "produit_id")
    private Produit produit;

    @ManyToOne
    @JoinColumn(nullable = false, name = "commande_id")
    private Commande commande;
    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;
}
