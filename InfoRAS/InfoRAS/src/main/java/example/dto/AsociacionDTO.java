package example.dto;

import example.domain.EnumAsociaciones;

public class AsociacionDTO {
    private Long id_Asociacion;
    private EnumAsociaciones nombre;
    private String descripcion;

    @Override
    public String toString() {
        return "AsociacionDTO{" +
                "id_Asociacion=" + id_Asociacion +
                ", nombre=" + nombre +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
