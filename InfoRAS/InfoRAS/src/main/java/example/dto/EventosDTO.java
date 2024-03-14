package example.dto;

import example.domain.TipoEvento;
import java.util.Date;
import lombok.Data;
@Data
public class EventosDTO {
    private Long id_eventos;
    private TipoEvento tipoEvento;
    private String titulo;
    private String Descripcion;
    private String Enlace;
    private Date fecha;
    private String lugar;

}
