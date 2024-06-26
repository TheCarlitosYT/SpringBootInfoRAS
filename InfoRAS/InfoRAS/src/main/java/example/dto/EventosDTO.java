package example.dto;

import example.domain.FormatoEvento;
import example.domain.TipoEvento;
import java.util.Date;
import lombok.Data;

@Data
public class EventosDTO {
    private Long id_eventos;
    private String titulo;
    private TipoEvento tipoEvento;
    private FormatoEvento formatoEvento;
    private String descripcion;
    private String enlace;
    private Date fecha;
    private String lugar;
    private Long id_usuario;
    private Long id_asociacion;

}
