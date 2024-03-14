package example.service.impl;

import example.domain.Documentos;
import example.domain.Eventos;
import example.dto.AsociacionDTO;
import example.service.AsociacionService;
import org.jvnet.hk2.annotations.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Validated
public class AsociacionServiceImpl implements AsociacionService {

    @Override
    public Optional<AsociacionDTO> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Set<AsociacionDTO> findAll() {
        return null;
    }

    @Override
    public Set<AsociacionDTO> findByTitulo(String titulo) {
        return null;
    }

    @Override
    public Set<AsociacionDTO> findByDescripcion(String descripcion) {
        return null;
    }

    @Override
    public List<Documentos> findDocumentosByUsuarioId(Long id) {
        return null;
    }

    @Override
    public List<Eventos> findEventosByUsuarioId(Long id) {
        return null;
    }

    @Override
    public AsociacionDTO addAsociacion(AsociacionDTO asociacionDTO) {
        return null;
    }

    @Override
    public AsociacionDTO modifyAsociacion(long id_Asociacion, AsociacionDTO newAsociacionDTO) {
        return null;
    }

    @Override
    public void deleteAsociacion(long id_Asociacion) {

    }
}
