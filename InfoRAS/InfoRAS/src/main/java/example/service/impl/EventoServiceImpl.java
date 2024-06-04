package example.service.impl;

import example.domain.Eventos;
import example.domain.FormatoEvento;
import example.domain.TipoEvento;
import example.domain.Usuario;
import example.dto.EventosDTO;
import example.exception.DocumentoNotFoundException;
import example.exception.EventoNotFoundException;
import example.mapper.EventoMapper;
import example.repository.EventoRepository;
import example.repository.UsuarioRepository;
import example.service.EventoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoMapper eventoMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Set<EventosDTO> findAll() {
        Set<Eventos> eventos = eventoRepository.findAll();
        return eventos.stream().map(eventoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Optional<EventosDTO> findById(long id_eventos) {
        Optional<Eventos> eventos = eventoRepository.findById(id_eventos);
        return eventos.map(eventoMapper::toDTO);
    }

    @Override
    public Set<EventosDTO> findByTipoEvento(TipoEvento tipoEvento) {
        Set<Eventos> eventos = eventoRepository.findByTipoEvento(tipoEvento);
        return eventos.stream().map(eventoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<EventosDTO> findByTitulo(String titulo) {
        Set<Eventos> eventos = eventoRepository.findByTitulo(titulo);
        return eventos.stream().map(eventoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<EventosDTO> findByFecha(Date fecha) {
        Set<Eventos> eventos = eventoRepository.findByFecha(fecha);
        return eventos.stream().map(eventoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<EventosDTO> findByLugar(String lugar) {
        Set<Eventos> eventos = eventoRepository.findByLugar(lugar);
        return eventos.stream().map(eventoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<EventosDTO> findByFormatoEvento(FormatoEvento formatoEvento) {
        Set<Eventos> eventos = eventoRepository.findByFormatoEvento(formatoEvento);
        return eventos.stream().map(eventoMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public EventosDTO addEvento(EventosDTO eventosDTO) {
        Eventos eventos = eventoMapper.toEntity(eventosDTO);

        Usuario usuario = usuarioRepository.findById(eventosDTO.getId_usuario())
                .orElseThrow(() -> new EventoNotFoundException(eventosDTO.getId_usuario()));
        eventos.setUsuario(usuario);

        eventos = eventoRepository.save(eventos);
        return eventoMapper.toDTO(eventos);
    }

    @Override
    public EventosDTO modifyEvento(long id_Eventos, EventosDTO newEventosDTO) {
        Eventos eventos = eventoRepository.findById(id_Eventos).orElseThrow(() -> new EventoNotFoundException(id_Eventos));

        Eventos newEventos = eventoMapper.toEntity(newEventosDTO);
        Usuario usuario = usuarioRepository.findById(newEventosDTO.getId_usuario())
                .orElseThrow(() -> new EventoNotFoundException(newEventosDTO.getId_usuario()));
        newEventos.setUsuario(usuario);

        newEventos.setId_eventos(eventos.getId_eventos());
        eventos = eventoRepository.save(newEventos);

        return eventoMapper.toDTO(eventos);
    }

    @Override
    public void deleteEvento(long id_Eventos) {
        eventoRepository.findById(id_Eventos)
                .orElseThrow(() -> new EventoNotFoundException(id_Eventos));
        eventoRepository.deleteById(id_Eventos);
    }

    @Override
    public EventosDTO modifyEventoSD(long id, EventosDTO data) {
        Eventos eventos = eventoRepository.findById(id)
                .orElseThrow(() -> new DocumentoNotFoundException("Documento no encontrado con el ID: " + id));

        eventos.setTitulo(data.getTitulo());
        eventos.setDescripcion(data.getDescripcion());
        eventos.setEnlace(data.getEnlace());
        eventos.setFecha(data.getFecha());
        eventos.setLugar(data.getLugar());


        Eventos savedEvent = eventoRepository.save(eventos);

        return eventoMapper.toDTO(savedEvent);
    }
}
