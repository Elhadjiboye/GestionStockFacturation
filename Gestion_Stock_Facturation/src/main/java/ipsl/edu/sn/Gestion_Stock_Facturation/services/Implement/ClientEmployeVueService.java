package ipsl.edu.sn.Gestion_Stock_Facturation.services.Implement;

import ipsl.edu.sn.Gestion_Stock_Facturation.dao.ClientEmployeRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.ClientEmployeVue;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ClientEmployeVueService implements UserDetailsService {

    private final ClientEmployeRepository clientEmployeRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientEmployeVue clientEmployeVue=clientEmployeRepository.findByEmail(email);
        if(clientEmployeVue==null){
            throw new UsernameNotFoundException("email not found with email: "+email);
        }
        return new org.springframework.security.core.userdetails.User(clientEmployeVue.getEmail(),clientEmployeVue.getMotDePasse(),
        Collections.singletonList(new SimpleGrantedAuthority(clientEmployeVue.getRole().getNom())));
    }
}
