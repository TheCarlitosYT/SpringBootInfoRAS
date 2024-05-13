package example.controller;

import example.domain.*;
import example.payload.request.*;
import example.payload.response.JwtResponse;
import example.payload.response.MessageResponse;
import example.repository.*;
import example.security.jwt.JwtUtils;
import example.security.services.UsuarioDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    AsociacionRepository asociacionRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUsuario(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        System.out.println("El token generado es " + jwt);

        UsuarioDetailsImpl usuarioDetails = (UsuarioDetailsImpl) authentication.getPrincipal();
        List<String> roles = usuarioDetails.getAuthorities().stream()
                /*.map(GrantedAuthority::getAuthority)*/ .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                usuarioDetails.getId(),
                usuarioDetails.getUsername(),
                usuarioDetails.getNombre(),
                usuarioDetails.getApellidos(),
                usuarioDetails.getPassword(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUsuario(@Valid @RequestBody SignupRequest signUpRequest) {
        if (usuarioRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new usuario's account
        Usuario usuario = new Usuario(
                EstadoUsuario.ACTIVO,
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getNombre(),
                signUpRequest.getApellidos()
        );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role usuarioRole = roleRepository.findByName(UserRoles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(usuarioRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        System.out.println("Este usuario será admin");
                        break;
                    default:
                        System.out.println(role + " usuario??");
                        Role usuarioRole = roleRepository.findByName(UserRoles.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(usuarioRole);
                }
            });
        }

        usuario.setRoles(roles);
        System.out.println("SpringBoot recibe datos Flutter: " + usuario);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

}
