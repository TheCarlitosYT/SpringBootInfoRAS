package example.service.impl;

import example.domain.Asociacion;
import example.domain.Documentos;
import example.domain.Eventos;
import example.domain.Usuario;
import example.dto.AsociacionDTO;
import example.exception.AsociacionNotFoundException;
import example.mapper.AsociacionMapper;
import example.repository.AsociacionRepository;
import example.service.AsociacionService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AsociacionServiceImpl implements AsociacionService {

    @Autowired
    private AsociacionRepository asociacionRepository;

    @Autowired
    private AsociacionMapper asociacionMapper;


    @Override
    public Optional<AsociacionDTO> findById(long id) {
        Optional<Asociacion> cliente = asociacionRepository.findById(id);
        return cliente.map(asociacionMapper::toDTO);
    }

    @Override
    public Set<AsociacionDTO> findAll() {
        Set<Asociacion> cliente = asociacionRepository.findAll();
        return cliente.stream().map(asociacionMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public Set<AsociacionDTO> findByTitulo(String titulo) {
        Set<Asociacion> cliente = asociacionRepository.findByTitulo(titulo);
        return cliente.stream().map(asociacionMapper::toDTO).collect(Collectors.toSet());
    }

    @Override
    public List<Documentos> findDocumentosByAsociacionId(Long asociacion_id) {
        Optional<Asociacion> asociacionOptional = asociacionRepository.findById(asociacion_id);
        if (asociacionOptional.isPresent()) {
            Asociacion asociacion = asociacionOptional.get();
            return asociacion.getDocumentos();
        } else {
            throw new AsociacionNotFoundException("Cliente with ID " + asociacion_id + " not found");
        }
    }

    @Override
    public List<Eventos> findEventosByAsociacionId(Long asociacion_id) {
        Optional<Asociacion> asociacionOptional = asociacionRepository.findById(asociacion_id);
        if (asociacionOptional.isPresent()) {
            Asociacion asociacion = asociacionOptional.get();
            return asociacion.getEventos();
        } else {
            throw new AsociacionNotFoundException("Cliente with ID " + asociacion_id + " not found");
        }
    }

    @Override
    public AsociacionDTO addAsociacion(AsociacionDTO asociacionDTO) {
        Asociacion asociacion = asociacionMapper.toEntity(asociacionDTO);
        asociacion = asociacionRepository.save(asociacion);
        return asociacionMapper.toDTO(asociacion);
    }

    @Override
    public AsociacionDTO modifyAsociacion(long id_Asociacion, AsociacionDTO newAsociacionDTO) {
        Asociacion asociacion = asociacionRepository.findById(id_Asociacion).orElseThrow(() -> new AsociacionNotFoundException(id_Asociacion));

        Asociacion nuevaAsociacion = asociacionMapper.toEntity(newAsociacionDTO);

        nuevaAsociacion.setId_asociacion(asociacion.getId_asociacion());
        asociacion = asociacionRepository.save(nuevaAsociacion);

        return asociacionMapper.toDTO(asociacion);
    }

    @Override
    public AsociacionDTO modifyAsociacionSD(long id, AsociacionDTO data) {
        Asociacion asociacion = asociacionRepository.findById(id)
                .orElseThrow(() -> new AsociacionNotFoundException("Cliente no encontrado con el ID: " + id));

        asociacion.setNombre(data.getNombre());
        asociacion.setDescripcion(data.getDescripcion());

        Asociacion savedAsociacion = asociacionRepository.save(asociacion);

        return asociacionMapper.toDTO(savedAsociacion);
    }

    @Override
    public void deleteAsociacion(long id_Asociacion) {
        asociacionRepository.findById(id_Asociacion)
                .orElseThrow(() -> new AsociacionNotFoundException(id_Asociacion));
        asociacionRepository.deleteById(id_Asociacion);
    }
}
