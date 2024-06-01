package example.dto;

import example.domain.TipoDoc;
import lombok.Data;
@Data
public class DocumentosDTO {
    private Long id_documento;
    private TipoDoc tipoDocumentacion;
    private String titulo;
    private String Descripcion;
    private String Enlace;
    private int id_usuario;
    private int id_asociacion;

}
