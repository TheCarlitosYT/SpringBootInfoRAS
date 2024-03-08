package example.controller;

import example.domain.*;
import example.payload.request.*;
import example.payload.response.JwtResponse;
import example.payload.response.MessageResponse;
import example.security.jwt.JwtUtils;
import example.security.services.ClienteDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
//http://localhost:8080/api/auth/signup?username=Pepe&email=pepe@gmail.com&password=1234&role=admin
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthUserController {
    @Autowired
    AuthenticationManager authenticationManager;

    /*@Autowired
    ClienteRepository clienteRepository;

    @Autowired
    IncidenciaRepository incidenciaRepository;

    @Autowired
    FavoritoRepository favoritoRepository;

    @Autowired
    RoleRepository roleRepository;*/

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateCliente(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        System.out.println("El token generado es " + jwt);

        ClienteDetailsImpl clienteDetails = (ClienteDetailsImpl) authentication.getPrincipal();
        List<String> roles = clienteDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                clienteDetails.getId(),
                clienteDetails.getUsername(),
                clienteDetails.getNombre(),
                clienteDetails.getApellidos(),
                clienteDetails.getPassword(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCliente(@Valid @RequestBody SignupRequest signUpRequest) {
        if (clienteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new cliente's account
        Cliente cliente = new Cliente(
                EstadoCliente.ACTIVO,
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getNombre(),
                signUpRequest.getApellidos()
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role clienteRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(clienteRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role clienteRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(clienteRole);
                }
            });
        }

        cliente.setRoles(roles);
        System.out.println("SpringBoot recibe datos Flutter: " + cliente);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

}
