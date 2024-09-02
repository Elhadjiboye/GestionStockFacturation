package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Stock {
    @Column(nullable = false)
    private int quantite;

    @Id
    @ManyToOne
    @JoinColumn(name = "magasin_id")
    private Magasin magasin;

    @Id
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @CreationTimestamp
    @Column(name = "date_creation", updatable = false)
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;
}
