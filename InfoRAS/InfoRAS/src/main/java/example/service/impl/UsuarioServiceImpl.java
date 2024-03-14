package example.service.impl;

import example.domain.Documentos;
import example.domain.Eventos;
import example.domain.Usuario;
import example.domain.EstadoUsuario;
import example.dto.DocumentosDTO;
import example.dto.EventosDTO;
import example.dto.UsuarioDTO;
import example.exception.UsuarioNotFoundException;
import example.mapper.UsuarioMapper;
import example.repository.UsuarioRepository;
import example.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Set<UsuarioDTO> findAll() {
        Set<Usuario> usuario = usuarioRepository.findAll();
        return usuario.stream().map(usuarioMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Optional<UsuarioDTO> findById(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(usuarioMapper::toDTO);
    }

    @Override
    public Optional<UsuarioDTO> findByUsername(String username) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        return usuario.map(usuarioMapper::toDTO);
    }

    @Override
    public Set<UsuarioDTO> findByEstado(EstadoUsuario estado){
        Set<Usuario> usuario = usuarioRepository.findByEstado(estado);
        return usuario.stream().map(usuarioMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<UsuarioDTO> findByNombre(String nombre) {
        Set<Usuario> usuario = usuarioRepository.findByNombre(nombre);
        return usuario.stream().map(usuarioMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<UsuarioDTO> findByApellidos(String apellidos) {
        Set<Usuario> usuario = usuarioRepository.findByApellidos(apellidos);
        return usuario.stream().map(usuarioMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public UsuarioDTO addUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioDTO modifyUsuario(long id, UsuarioDTO nuevoUsuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

        Usuario newUsuario = usuarioMapper.toEntity(nuevoUsuarioDTO);

        newUsuario.setId_usuario(usuario.getId_usuario());
        usuario = usuarioRepository.save(newUsuario);

        return usuarioMapper.toDTO(usuario);
    }


    @Override
    public UsuarioDTO modifyUsuarioData(long id, UsuarioDTO data) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado con el ID: " + id));

        usuario.setUsername(data.getUsername());
        usuario.setNombre(data.getNombre());
        usuario.setApellidos(data.getApellidos());
        Usuario savedUsuario = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(savedUsuario);
    }

    @Override
    public Set<UsuarioDTO> findUsuarioByNombreAndApellidos(String nombre, String apellidos) {
        String query = "SELECT * FROM usuarios WHERE nombre = ? AND apellidos = ?";
        Set<Usuario> usuario = usuarioRepository.findUsuarioByNombreAndApellidos(nombre, apellidos);
        return usuario.stream().map(usuarioMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public void deleteUsuario(long id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Documentos> findDocumentosByUsuarioId(Long id) {
        return null;
    }

    @Override
    public List<Eventos> findEventosByUsuarioId(Long id) {
        return null;
    }
}
