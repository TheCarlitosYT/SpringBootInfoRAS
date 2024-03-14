package example.service;


import example.domain.TipoDoc;
import example.dto.DocumentosDTO;
import example.dto.DocumentosDTO;
import jakarta.validation.Valid;


import java.util.Optional;
import java.util.Set;

public interface DocumentoService {
    Set<DocumentosDTO> findAll();

    Optional<DocumentosDTO> findById(long id_DocumentosDTO);

    Set<DocumentosDTO> findByTitulo(String titulo);

    Set<DocumentosDTO> findByTipoDocumento(TipoDoc tipoDoc);

    Set<DocumentosDTO> findByDescripcion(String descripcion);

    Set<DocumentosDTO> findByEnlace(String enlace);

    DocumentosDTO addEvento(@Valid DocumentosDTO documentosDTO);
    DocumentosDTO modifyEvento(long id_Eventos, DocumentosDTO newDocumentosDTO);
    void deleteEvento(long id_Eventos);
}
