package ipsl.edu.sn.Gestion_Stock_Facturation.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Entity
@Data
@Table(name = "client_employe_vue")
public class ClientEmployeVue {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "code_zip")
    private String codeZip;

    @Column(name = "etat")
    private String etat;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "mot_de_passe")
    private String motDePasse;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "ville")
    private String ville;

    @Column(name = "date_modification")
    private Timestamp dateModification;

    @Column(name = "date_creation")
    private Timestamp dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role") // Assurez-vous que le nom correspond à celui de la vue
    private Role role;
}
