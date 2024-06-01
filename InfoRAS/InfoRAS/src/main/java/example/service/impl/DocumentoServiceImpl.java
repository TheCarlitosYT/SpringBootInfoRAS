package example.service.impl;

import example.domain.Asociacion;
import example.domain.Documentos;
import example.domain.TipoDoc;
import example.domain.Usuario;
import example.dto.DocumentosDTO;
import example.exception.DocumentoNotFoundException;
import example.exception.EventoNotFoundException;
import example.mapper.AsociacionMapper;
import example.mapper.DocumentoMapper;
import example.repository.AsociacionRepository;
import example.repository.DocumentoRepository;
import example.repository.UsuarioRepository;
import example.service.DocumentoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class DocumentoServiceImpl implements DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private DocumentoMapper documentoMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Set<DocumentosDTO> findAll() {
        Set<Documentos> documentos = documentoRepository.findAll();
        return documentos.stream().map(documentoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Optional<DocumentosDTO> findById(long id_DocumentosDTO) {
        Optional<Documentos> documentos = documentoRepository.findById(id_DocumentosDTO);
        return documentos.map(documentoMapper::toDTO);
    }

    @Override
    public Set<DocumentosDTO> findByTitulo(String titulo) {
        Set<Documentos> documentos = documentoRepository.findByTitulo(titulo);
        return documentos.stream().map(documentoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<DocumentosDTO> findByTipoDocumentacion(TipoDoc tipoDoc) {
        Set<Documentos> documentos = documentoRepository.findByTipoDocumentacion(tipoDoc);
        return documentos.stream().map(documentoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public DocumentosDTO addDocumento(DocumentosDTO documentosDTO) {
        Documentos documentos = documentoMapper.toEntity(documentosDTO);

        Usuario usuario = usuarioRepository.findById(documentosDTO.getId_usuario())
                .orElseThrow(() -> new EventoNotFoundException(documentosDTO.getId_usuario()));
        documentos.setUsuario(usuario);

        documentos = documentoRepository.save(documentos);
        return documentoMapper.toDTO(documentos);
    }

    @Override
    public DocumentosDTO modifyDocumento(long id_Documento, DocumentosDTO newDocumentosDTO) {
        Documentos documentos = documentoRepository.findById(id_Documento).orElseThrow(() -> new DocumentoNotFoundException(id_Documento));

        Documentos newDocumentos = documentoMapper.toEntity(newDocumentosDTO);

        Usuario usuario = usuarioRepository.findById(newDocumentosDTO.getId_usuario())
                .orElseThrow(() -> new EventoNotFoundException(newDocumentosDTO.getId_usuario()));
        documentos.setUsuario(usuario);

        newDocumentos.setId_documento(documentos.getId_documento());
        documentos = documentoRepository.save(newDocumentos);

        return documentoMapper.toDTO(documentos);
    }

    @Override
    public void deleteDocumento(long id_Documentos) {
        documentoRepository.findById(id_Documentos)
                .orElseThrow(() -> new DocumentoNotFoundException(id_Documentos));
        documentoRepository.deleteById(id_Documentos);
    }

    @Override
    public DocumentosDTO modifyDocumentoSD(long id, DocumentosDTO data) {
        Documentos documentos = documentoRepository.findById(id)
                .orElseThrow(() -> new DocumentoNotFoundException("Documento no encontrado con el ID: " + id));

        documentos.setTitulo(data.getTitulo());
        documentos.setDescripcion(data.getDescripcion());
        documentos.setEnlace(data.getEnlace());
        documentos.setTipoDocumentacion(data.getTipoDocumentacion());

        Documentos savedDoc = documentoRepository.save(documentos);

        return documentoMapper.toDTO(savedDoc);
    }
}
