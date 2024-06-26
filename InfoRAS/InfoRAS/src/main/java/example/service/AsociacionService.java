package example.service;


import example.domain.Documentos;
import example.domain.Eventos;
import example.dto.AsociacionDTO;
import jakarta.validation.Valid;
import org.jvnet.hk2.annotations.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface AsociacionService {

    Optional<AsociacionDTO> findById(long id);
    Set<AsociacionDTO> findAll();
    Set<AsociacionDTO> findByNombre(String nombre);
    List<Documentos> findDocumentosByAsociacionId(Long id);
    List<Eventos> findEventosByAsociacionId(Long id);

    AsociacionDTO addAsociacion(@Valid AsociacionDTO asociacionDTO);
    AsociacionDTO modifyAsociacion(long id_Asociacion, AsociacionDTO newAsociacionDTO);

    //SD = SpecificData
    AsociacionDTO modifyAsociacionSD(long id_Asociacion, AsociacionDTO datosE);
    void deleteAsociacion(long id_Asociacion);
}
