package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int date_commande;
    @Column(nullable = false)
    private short statut;
    @Column(nullable = false, length = 255)
    private String date_livraison;
    @Column(length = 255)
    private String date_livraison_voulue;

    @ManyToOne
    @JoinColumn(nullable = false, name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(nullable = false, name = "magasin_id")
    private Magasin magasin;

    @ManyToOne
    @JoinColumn(name = "vendeur_id")
    private Employe employe;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private Set<ArticleCommande> articlesCommande;

    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;
}
