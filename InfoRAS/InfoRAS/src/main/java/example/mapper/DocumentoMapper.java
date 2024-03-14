package example.mapper;


import example.domain.Documentos;
import example.dto.DocumentosDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentoMapper {
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public DocumentosDTO toDTO(Documentos documentos) {
        return modelMapper.map(documentos, DocumentosDTO.class);
    }

    public Documentos toEntity(DocumentosDTO documentosDTO) {
        return modelMapper.map(documentosDTO, Documentos.class);
    }

}