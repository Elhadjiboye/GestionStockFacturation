package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Employe extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employe manager;

    @OneToMany(mappedBy = "manager")
    private Set<Employe> subordonnés;

    @ManyToOne
    @JoinColumn(nullable = false, name = "magasin_id")
    private Magasin magasin;



    @OneToMany(mappedBy = "employe")
    private Set<Commande> commandes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Employe_roles",
            joinColumns = @JoinColumn(name = "id_employe"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;

}
