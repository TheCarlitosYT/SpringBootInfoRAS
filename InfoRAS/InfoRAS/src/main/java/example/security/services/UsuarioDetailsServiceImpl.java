package example.security.services;

import example.domain.Usuario;
import example.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//TODO REPOSITORY
@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository clienteRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario cliente = clienteRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UsuarioDetailsImpl.build(cliente);
    }
}
