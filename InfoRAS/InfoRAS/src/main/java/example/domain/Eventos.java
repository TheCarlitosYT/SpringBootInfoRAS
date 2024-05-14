package example.domain;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="Eventos")
public class Eventos {

    @Schema(description = "Identificador del evento", example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eventos")
    private Long id_eventos;
    @Schema(description = "Titulo del evento", example = "Presentación de una nueva hormona", required = true)
    @NotBlank
    @Column(name = "titulo")
    private String titulo;
    @Schema(description = "Estado del evento", example = "Noonan_General", required = false)
    @Column(name = "tipo")
    private TipoEvento tipoEvento;

    @Schema(description = "Enlace del evento", example = "https://example.com.", required = false)
    @NotBlank
    @Column(name = "Enlace")
    private String enlace;

    @Schema(description = "Descripción del evento (opcional)", example = "Esta es una descripción", required = false)
    @Column(name = "Descripcion")
    private String descripcion;

    @Schema(description = "Fecha del evento", example = "2024-05-05T12:00:00", required = true)
    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull
    @Column(name = "fecha")
    private Date fecha;

    @Schema(description = "Lugar donde se celebrará el evento, puedes poner lo que sea", example = "ACTIVO", required = false)
    @Column(name = "lugar")
    private String lugar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asociacion_id")
    private Asociacion asociacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Eventos(Long id_eventos, TipoEvento tipoEvento, String titulo, String enlace, String descripcion, Date fecha, String lugar, Asociacion asociacion, Usuario usuario) {
        this.id_eventos = id_eventos;
        this.tipoEvento = tipoEvento;
        this.titulo = titulo;
        this.enlace = enlace;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.lugar = lugar;
        this.asociacion = asociacion;
        this.usuario = usuario;
    }

    public Eventos() {
    }

    public Long getId_eventos() {
        return id_eventos;
    }

    public void setId_eventos(Long id_eventos) {
        this.id_eventos = id_eventos;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public String toString() {
        return "Eventos{" +
                "id_eventos=" + id_eventos +
                ", tipoEvento=" + tipoEvento +
                ", titulo='" + titulo + '\'' +
                ", enlace='" + enlace + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", lugar='" + lugar + '\'' +
                ", asociacion=" + asociacion +
                ", usuario=" + usuario +
                '}';
    }
}
