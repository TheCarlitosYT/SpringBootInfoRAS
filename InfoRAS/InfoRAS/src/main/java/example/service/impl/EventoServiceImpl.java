package example.service.impl;

import example.domain.TipoEvento;
import example.dto.EventosDTO;
import example.service.EventoService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
@Validated
public class EventoServiceImpl implements EventoService {
    @Override
    public Set<EventosDTO> findAll() {
        return null;
    }

    @Override
    public Optional<EventosDTO> findById(long id_eventos) {
        return Optional.empty();
    }

    @Override
    public Set<EventosDTO> findByTipoEvento(TipoEvento tipoEvento) {
        return null;
    }

    @Override
    public Set<EventosDTO> findByTitulo(String titulo) {
        return null;
    }

    @Override
    public Set<EventosDTO> findByDescripcion(String descripcion) {
        return null;
    }

    @Override
    public Set<EventosDTO> findByEnlace(String enlace) {
        return null;
    }

    @Override
    public Set<EventosDTO> findByFecha(Date fecha) {
        return null;
    }

    @Override
    public Set<EventosDTO> findByLugar(String lugar) {
        return null;
    }

    @Override
    public EventosDTO addEvento(EventosDTO eventosDTO) {
        return null;
    }

    @Override
    public EventosDTO modifyEvento(long id_Eventos, EventosDTO newEventosDTO) {
        return null;
    }

    @Override
    public void deleteEvento(long id_Eventos) {

    }
}
