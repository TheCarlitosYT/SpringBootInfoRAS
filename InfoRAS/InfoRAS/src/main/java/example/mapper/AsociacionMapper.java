package example.mapper;

import example.domain.Asociacion;
import example.dto.AsociacionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsociacionMapper {
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public AsociacionDTO toDTO(Asociacion asociacion) {
        return modelMapper.map(asociacion, AsociacionDTO.class);
    }

    public Asociacion toEntity(AsociacionDTO asociacionDTO) {
        return modelMapper.map(asociacionDTO, Asociacion.class);
    }
}