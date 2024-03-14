package example.service;

import example.domain.Asociacion;
import example.domain.Documentos;
import example.domain.Eventos;
import example.dto.AsociacionDTO;
import example.dto.DocumentosDTO;
import example.dto.EventosDTO;
import jakarta.validation.Valid;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AsociacionService {

    Optional<AsociacionDTO> findById(long id);
    Set<AsociacionDTO> findAll();
    Set<AsociacionDTO> findByTitulo(String titulo);
    Set<AsociacionDTO> findByDescripcion(String descripcion);

    List<Documentos> findDocumentosByUsuarioId(Long id);
    List<Eventos> findEventosByUsuarioId(Long id);

    AsociacionDTO addAsociacion(@Valid AsociacionDTO asociacionDTO);
    AsociacionDTO modifyAsociacion(long id_Asociacion, AsociacionDTO newAsociacionDTO);
    void deleteAsociacion(long id_Asociacion);
}
