package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Entity
@Data
@RequiredArgsConstructor
public class Client extends Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 255)
    private String code_zip;
    @Column(length = 255)
    private String etat;
    @Column(length = 255)
    private String ville;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Client_roles",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_role")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Commande> commandes;



    @UpdateTimestamp
    @Column(name = "date_modification")
    private LocalDateTime dateModification;

}
