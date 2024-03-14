package example.repository;

import example.domain.Asociacion;
import example.domain.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
@Repository
public interface AsociacionRepository extends CrudRepository<Asociacion, Long> {
    Set<Asociacion> findAll();
    Optional<Asociacion> findById(long id);
    Set<Asociacion> findByTitulo(String titulo);
    Set<Asociacion> findByDescripcion(String descripcion);

    @Query(value = "SELECT id_asociacion, titulo, descripcion FROM asociacion a WHERE a.titulo LIKE :titulo", nativeQuery = true)
    Set<Asociacion> findAsociacionByTitulo(String titulo);
}
