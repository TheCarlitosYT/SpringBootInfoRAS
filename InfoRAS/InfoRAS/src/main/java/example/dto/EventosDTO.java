package example.dto;

import example.domain.TipoEvento;
import java.util.Date;

public class EventosDTO {
    private Long id_eventos;
    private TipoEvento tipoEvento;
    private String titulo;
    private String Descripcion;
    private String Enlace;
    private Date fecha;
    private String lugar;

    @Override
    public String toString() {
        return "EventosDTO{" +
                "id_eventos=" + id_eventos +
                ", tipoEvento=" + tipoEvento +
                ", titulo='" + titulo + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Enlace='" + Enlace + '\'' +
                ", fecha=" + fecha +
                ", lugar='" + lugar + '\'' +
                '}';
    }
}
