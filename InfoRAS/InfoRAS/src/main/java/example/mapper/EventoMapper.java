package example.mapper;

import example.domain.Eventos;
import example.dto.EventosDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {
    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    public EventosDTO toDTO(Eventos eventos) {
        EventosDTO eventosDTO = modelMapper.map(eventos, EventosDTO.class);
        eventosDTO.setId_usuario(eventos.getUsuario().getId_usuario());
        return eventosDTO;
    }

    public Eventos toEntity(EventosDTO eventosDTO) {
        return modelMapper.map(eventosDTO, Eventos.class);
    }
}
