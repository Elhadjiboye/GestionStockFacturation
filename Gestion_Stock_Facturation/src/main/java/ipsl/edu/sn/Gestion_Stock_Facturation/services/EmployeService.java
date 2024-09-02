package ipsl.edu.sn.Gestion_Stock_Facturation.services;

import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Employe;

import java.util.List;

public interface EmployeService {
    Employe addEmploye(Employe employe) ;
    List<Employe> getAllEmployes();
    Employe updateEmploye(Integer id, Employe employe) ;
    String deleteEmploye(Integer id) ;
    Employe getOneEmploye(Integer id) ;
}
