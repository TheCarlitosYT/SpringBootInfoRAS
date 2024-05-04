package example.service;


import example.domain.TipoDoc;
import example.dto.DocumentosDTO;
import jakarta.validation.Valid;
import org.jvnet.hk2.annotations.Service;


import java.util.Optional;
import java.util.Set;

@Service
public interface DocumentoService {
    Set<DocumentosDTO> findAll();

    Optional<DocumentosDTO> findById(long id_DocumentosDTO);

    Set<DocumentosDTO> findByTitulo(String titulo);

    Set<DocumentosDTO> findByTipoDocumentacion(TipoDoc tipoDoc);

    DocumentosDTO addDocumento(@Valid DocumentosDTO documentosDTO);
    DocumentosDTO modifyDocumento(long id_Documentos, DocumentosDTO newDocumentosDTO);
    void deleteDocumento(long id_Documentos);

    DocumentosDTO modifyDocumentoSD(long id, DocumentosDTO data);
}
