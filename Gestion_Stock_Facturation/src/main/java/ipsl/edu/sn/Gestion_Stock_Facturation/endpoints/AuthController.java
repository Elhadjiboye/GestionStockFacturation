package ipsl.edu.sn.Gestion_Stock_Facturation.endpoints;

import ipsl.edu.sn.Gestion_Stock_Facturation.Configuration.JwtUtils;
import ipsl.edu.sn.Gestion_Stock_Facturation.dao.ClientEmployeRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.dao.ClientRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.dao.EmployeRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.dao.RoleRepository;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Client;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.ClientEmployeVue;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Employe;
import ipsl.edu.sn.Gestion_Stock_Facturation.entities.Role;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.ClientService;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.EmployeService;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.Implement.ClientServiceImplem;
import ipsl.edu.sn.Gestion_Stock_Facturation.services.Implement.EmployeServiceImplem;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final ClientEmployeRepository clientEmployeRepository;
    private final RoleRepository roleRepository;
    private final ClientService clientService;
    private final EmployeService employeService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser( @RequestBody Client client) {
        Role userRole = roleRepository.findByNom("CLIENT")
                .orElseThrow(() -> new RuntimeException("Role Client not found"));
        if (clientEmployeRepository.findByEmail(client.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        client.setRoles(Set.of(userRole));
        client.setMotDePasse(passwordEncoder.encode(client.getMotDePasse())); // Encodez le mot de passe
        clientService.addClient(client);

        return ResponseEntity.ok("Client registered successfully with Client role");
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerUser(@RequestBody Employe employe) {
        Role userRole = roleRepository.findByNom("EMPLOYE")
                .orElseThrow(() -> new RuntimeException("Role Employe not found"));
        if (clientEmployeRepository.findByEmail(employe.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        employe.setRoles(Set.of(userRole));
        employe.setMotDePasse(passwordEncoder.encode(employe.getMotDePasse())); // Encodez le mot de passe
        employeService.addEmploye(employe);

        return ResponseEntity.ok("Employe registered successfully with Employe role");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody ClientEmployeVue clientEmployeVue) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(clientEmployeVue.getEmail(), clientEmployeVue.getMotDePasse()));
            if (authentication.isAuthenticated()) {
                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(clientEmployeVue.getEmail()));
                authData.put("token_type", "Bearer");

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

                if (authorities.contains(new SimpleGrantedAuthority("EMPLOYE"))) {
                    return ResponseEntity.ok("Login successful. Welcome, Employe! et voici les infos du jwt :"+ authData);
                } else if (authorities.contains(new SimpleGrantedAuthority("CLIENT"))) {
                    return ResponseEntity.ok("Login successful. Welcome, Client! et voici les infos du jwt :"+ authData);
                }
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or password");
            }catch(AuthenticationException e){
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or password");
            }
        }
}
