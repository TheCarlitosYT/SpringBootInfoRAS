package example.mapper;

import example.domain.Eventos;
import example.dto.EventosDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {
    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    public EventosDTO toDTO(Eventos eventos) {
        return modelMapper.map(eventos, EventosDTO.class);
    }

    public Eventos toEntity(EventosDTO eventosDTO) {
        return modelMapper.map(eventosDTO, Eventos.class);
    }
}
