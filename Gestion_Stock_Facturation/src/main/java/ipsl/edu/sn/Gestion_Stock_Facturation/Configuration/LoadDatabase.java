package ipsl.edu.sn.Gestion_Stock_Facturation.Configuration;

import ipsl.edu.sn.Gestion_Stock_Facturation.dao.RoleRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByNom("CLIENT").isEmpty()) {
                Role clientRole = new Role();
                clientRole.setNom("CLIENT");
                roleRepository.save(clientRole);
            }

            if (roleRepository.findByNom("EMPLOYE").isEmpty()) {
                Role employeRole = new Role();
                employeRole.setNom("EMPLOYE");
                roleRepository.save(employeRole);
            }
        };
    }
}
