package example.dto;

import example.domain.EnumAsociaciones;
import lombok.Data;
@Data
public class AsociacionDTO {
    private Long id_Asociacion;
    private EnumAsociaciones nombre;
    private String descripcion;

}
