package example.mapper;


import example.domain.Documentos;
import example.dto.DocumentosDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {
    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    public DocumentosDTO toDTO(Documentos documentos) {
        DocumentosDTO documentosDTO = modelMapper.map(documentos, DocumentosDTO.class);
        documentosDTO.setId_usuario(documentos.getUsuario().getId_usuario());
        return documentosDTO;    }

    public Documentos toEntity(DocumentosDTO documentosDTO) {
        return modelMapper.map(documentosDTO, Documentos.class);
    }

}