package example.dto;

import example.domain.EstadoUsuario;
import lombok.Data;
@Data
public class UsuarioDTO {
    private Long id_usuario;
    private EstadoUsuario estado;
    private String username;
    private String password; // TODO: se quitará la contraseña para que no se pasé al cliente al devolver un cliente y tenga una capa más de seguridad
    private String nombre;
    private String apellidos;
    private int usuario_id;
    private int asociacion_id;
}
