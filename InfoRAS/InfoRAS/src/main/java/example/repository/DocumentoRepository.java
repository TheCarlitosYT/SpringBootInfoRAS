package example.repository;
import example.domain.Documentos;
import example.domain.TipoDoc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DocumentoRepository extends CrudRepository<Documentos, Long> {

    Set<Documentos> findAll();

    Optional<Documentos> findById(long id_Documentos);

    Set<Documentos> findByTitulo(String titulo);

    Set<Documentos> findByTipoDocumento(TipoDoc tipoDoc);

    Set<Documentos> findByDescripcion(String descripcion);

    Set<Documentos> findByEnlace(String enlace);
}
