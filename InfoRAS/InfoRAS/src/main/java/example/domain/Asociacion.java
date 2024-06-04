package example.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Asociacion")
public class Asociacion {

    @Schema(description = "Identificador del usuario null", example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asociacion")
    private Long id_asociacion;

    @Schema(description = "Nombre de la asociación", example = "FEDERAS", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    private EnumAsociaciones nombre;

    @Schema(description = "Descripcion de la asociacion", example = "ACTIVO", required = false)
    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private List<Eventos> eventos;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id")
    private List<Documentos> documentos;

    public Asociacion(Long id_asociacion, EnumAsociaciones nombre, String descripcion, Eventos eventos, Documentos documentos) {
        this.id_asociacion = id_asociacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Asociacion() {
    }

    public Long getId_asociacion() {
        return id_asociacion;
    }

    public void setId_asociacion(Long id_asociacion) {
        this.id_asociacion = id_asociacion;
    }

    public EnumAsociaciones getNombre() {
        return nombre;
    }

    public void setNombre(EnumAsociaciones nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Eventos> getEventos() {
        return eventos;
    }

    public void setEventos(List<Eventos> eventos) {
        this.eventos = eventos;
    }

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }

    @Override
    public String toString() {
        return "Asociacion{" +
                "id_asociacion=" + id_asociacion +
                ", nombre=" + nombre +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
