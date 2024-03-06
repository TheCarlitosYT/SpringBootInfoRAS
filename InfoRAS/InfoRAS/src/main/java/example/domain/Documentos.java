package example.domain;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name="Documentos")
public class Documentos {

    @Schema(description = "Identificador del documento", example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documentos")
    private Long id_documento;

    @Schema(description = "Estado del post", example = "Noonan_General", required = false)
    @Column(name = "tipo")
    private TipoDoc tipoDocumentacion;

    //email pero se transforma en el backend a username
    @Schema(description = "Titulo del documento", example = "La nueva medicina...", required = true)
    @NotBlank
    @Column(name = "titulo")
    private String titulo;

    @Schema(description = "Enlace de la documentación", example = "https://example.com.", required = true)
    @NotBlank
    @Column(name = "Enlace")
    private String enlace;

    @Schema(description = "Descripción del post (opcional)", example = "Esta es una descripción", required = false)
    @Column(name = "Descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asociacion_id")
    private Asociacion asociacion;

    public Documentos(Long id_documento, TipoDoc tipoDocumentacion, String titulo, String enlace, String descripcion) {
        this.id_documento = id_documento;
        this.tipoDocumentacion = tipoDocumentacion;
        this.titulo = titulo;
        this.enlace = enlace;
        this.descripcion = descripcion;
    }

    public Documentos() {
    }


    public Long getId_documento() {
        return id_documento;
    }

    public void setId_documento(Long id_documento) {
        this.id_documento = id_documento;
    }

    public TipoDoc getTipoDocumentacion() {
        return tipoDocumentacion;
    }

    public void setTipoDocumentacion(TipoDoc tipoDocumentacion) {
        this.tipoDocumentacion = tipoDocumentacion;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Asociacion getAsociacion() {
        return asociacion;
    }

    public void setAsociacion(Asociacion asociacion) {
        this.asociacion = asociacion;
    }

    @Override
    public String toString() {
        return "Documentos{" +
                "id_documento=" + id_documento +
                ", tipoDocumentacion=" + tipoDocumentacion +
                ", titulo='" + titulo + '\'' +
                ", enlace='" + enlace + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
