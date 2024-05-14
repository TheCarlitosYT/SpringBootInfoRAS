package example.service;

import example.domain.TipoEvento;
import example.dto.EventosDTO;
import jakarta.validation.Valid;
import org.jvnet.hk2.annotations.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public interface EventoService {
    Optional<EventosDTO> findById(long id_eventos);

    Set<EventosDTO> findAll();

    Set<EventosDTO> findByTipoEvento(TipoEvento tipoEvento);

    Set<EventosDTO> findByTitulo(String titulo);

    Set<EventosDTO> findByFecha(Date fecha);

    Set<EventosDTO> findByLugar(String lugar);

    EventosDTO addEvento(@Valid EventosDTO eventosDTO);
    EventosDTO modifyEvento(long id_Eventos, EventosDTO newEventosDTO);
    void deleteEvento(long id_Eventos);
    EventosDTO modifyEventoSD(long id, EventosDTO data);
}
