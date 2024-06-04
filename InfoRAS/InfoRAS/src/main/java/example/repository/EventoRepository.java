package example.repository;
import example.domain.Eventos;
import example.domain.FormatoEvento;
import example.domain.TipoEvento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EventoRepository extends CrudRepository<Eventos, Long> {

    Set<Eventos> findAll();

    Optional<Eventos> findById(long id_eventos);

    Set<Eventos> findByTipoEvento(TipoEvento tipoEvento);

    Set<Eventos> findByTitulo(String titulo);

    Set<Eventos> findByDescripcion(String descripcion);

    Set<Eventos> findByEnlace(String enlace);

    Set<Eventos> findByFecha(Date fecha);

    Set<Eventos> findByLugar(String lugar);

    Set<Eventos> findByFormatoEvento(FormatoEvento formatoEvento);

}
