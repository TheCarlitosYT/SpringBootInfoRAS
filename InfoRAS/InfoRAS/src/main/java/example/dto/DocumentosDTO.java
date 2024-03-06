package example.dto;

import example.domain.TipoDoc;

public class DocumentosDTO {
    private Long id_documento;
    private TipoDoc tipoDocumentacion;
    private String titulo;
    private String Descripcion;
    private String Enlace;

    @Override
    public String toString() {
        return "DocumentosDTO{" +
                "id_documento=" + id_documento +
                ", tipoDocumentacion=" + tipoDocumentacion +
                ", titulo='" + titulo + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Enlace='" + Enlace + '\'' +
                '}';
    }
}
