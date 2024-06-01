package example.dto;

import example.domain.TipoEvento;
import java.util.Date;
import lombok.Data;
@Data
public class EventosDTO {
    private Long id_eventos;
    private String titulo;
    private TipoEvento tipoEvento;
    private String descripcion;
    private String enlace;
    private Date fecha;
    private String lugar;
    private int id_usuario;
    private int id_asociacion;

}
