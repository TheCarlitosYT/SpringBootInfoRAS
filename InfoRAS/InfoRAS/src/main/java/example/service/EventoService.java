package example.service;

import example.domain.TipoEvento;
import example.dto.EventosDTO;
import example.dto.EventosDTO;
import jakarta.validation.Valid;


import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface EventoService {
    Set<EventosDTO> findAll();
    Optional<EventosDTO> findById(long id_eventos);
    Set<EventosDTO> findByTipoEvento(TipoEvento tipoEvento);

    Set<EventosDTO> findByTitulo(String titulo);

    Set<EventosDTO> findByDescripcion(String descripcion);

    Set<EventosDTO> findByEnlace(String enlace);

    Set<EventosDTO> findByFecha(Date fecha);

    Set<EventosDTO> findByLugar(String lugar);

    EventosDTO addEvento(@Valid EventosDTO eventosDTO);
    EventosDTO modifyEvento(long id_Eventos, EventosDTO newEventosDTO);
    void deleteEvento(long id_Eventos);
}
