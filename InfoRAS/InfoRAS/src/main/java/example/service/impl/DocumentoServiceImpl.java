package example.service.impl;

import example.domain.TipoDoc;
import example.dto.DocumentosDTO;
import example.service.DocumentoService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Set;

@Service
@Validated
public class DocumentoServiceImpl implements DocumentoService {
    @Override
    public Set<DocumentosDTO> findAll() {
        return null;
    }

    @Override
    public Optional<DocumentosDTO> findById(long id_DocumentosDTO) {
        return Optional.empty();
    }

    @Override
    public Set<DocumentosDTO> findByTitulo(String titulo) {
        return null;
    }

    @Override
    public Set<DocumentosDTO> findByTipoDocumento(TipoDoc tipoDoc) {
        return null;
    }

    @Override
    public Set<DocumentosDTO> findByDescripcion(String descripcion) {
        return null;
    }

    @Override
    public Set<DocumentosDTO> findByEnlace(String enlace) {
        return null;
    }

    @Override
    public DocumentosDTO addEvento(DocumentosDTO documentosDTO) {
        return null;
    }

    @Override
    public DocumentosDTO modifyEvento(long id_Eventos, DocumentosDTO newDocumentosDTO) {
        return null;
    }

    @Override
    public void deleteEvento(long id_Eventos) {

    }
}
