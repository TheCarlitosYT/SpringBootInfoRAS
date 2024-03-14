package example.service;

import example.domain.Documentos;
import example.domain.EstadoUsuario;
import example.domain.Eventos;
import example.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.jvnet.hk2.annotations.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface UsuarioService {

    Optional<UsuarioDTO> findById(long id);

    Set<UsuarioDTO> findAll();
    Optional<UsuarioDTO> findByUsername(String username);

    Set<UsuarioDTO> findByEstado(EstadoUsuario estado);
    Set<UsuarioDTO> findByNombre(String nombre);
    Set<UsuarioDTO> findByApellidos(String apellidos);

    UsuarioDTO addUsuario(@Valid UsuarioDTO usuarioDTO);
    UsuarioDTO modifyUsuario(long id, UsuarioDTO usuarioDTO);
    UsuarioDTO modifyUsuarioData(long id, UsuarioDTO data);

    Set<UsuarioDTO> findUsuarioByNombreAndApellidos(String nombre, String apellidos);

    void deleteUsuario(long id);

    List<Documentos> findDocumentosByUsuarioId(Long id);
    List<Eventos> findEventosByUsuarioId(Long id);
}
