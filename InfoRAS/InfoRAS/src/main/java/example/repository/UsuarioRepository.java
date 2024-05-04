package example.repository;

import example.domain.Documentos;
import example.domain.Eventos;
import example.domain.Usuario;
import example.domain.EstadoUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

    Set<Usuario> findAll();
    Optional<Usuario> findById(long id);
    Set<Usuario> findByEstado(EstadoUsuario estado);
    Set<Usuario> findByNombre(String nombre);
    Set<Usuario> findByApellidos(String apellidos);

    Usuario findUsuarioByUsername(String username);

    Optional<Usuario> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query(value = "SELECT id_usuario,password,username,nombre,apellidos FROM usuarios c WHERE c.nombre LIKE :nombre AND c.apellidos LIKE :apellidos", nativeQuery = true)
    Set<Usuario> findUsuarioByNombreAndApellidos(String nombre, String apellidos);

    @Query("SELECT i FROM Documentos i WHERE i.usuario.id_usuario = :usuarioId")
    List<Documentos> findDocumentosByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT f FROM Eventos f WHERE f.usuario.id_usuario = :usuarioId")
    List<Eventos> findEventosByUsuarioId(@Param("usuarioId") Long idUsuario);

}
