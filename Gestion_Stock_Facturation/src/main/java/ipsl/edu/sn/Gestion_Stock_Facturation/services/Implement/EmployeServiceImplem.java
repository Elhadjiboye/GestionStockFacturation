package ipsl.edu.sn.Gestion_Stock_Facturation.services.Implement;

import ipsl.edu.sn.Gestion_Stock_Facturation.dao.EmployeRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Employe;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.EmployeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImplem implements EmployeService {

    public final EmployeRepository employeRepository;

    @Override
    public Employe addEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @Override
    public Employe updateEmploye(Integer id, Employe employe) {
        if (!employeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employe " + id + "n'existe pas");
        }
        employe.setId(id);
        return employeRepository.save(employe);
    }

    @Override
    public String deleteEmploye(Integer id) {
        if (!employeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employe " + id + "n'existe pas");
        }
        employeRepository.deleteById(id);
        return "Employe "+ id +"Supprimer avec succees";

    }

    @Override
    public Employe getOneEmploye(Integer id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employe n'existe pas avec l'id : " + id));
    }
}
