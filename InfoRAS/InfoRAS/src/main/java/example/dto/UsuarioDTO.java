package example.dto;

import example.domain.EstadoUsuario;

public class UsuarioDTO {
    private Long id_cliente;
    private EstadoUsuario estado;
    private String username;
    private String password; // TODO: se quitará la contraseña para que no se pasé al cliente al devolver un cliente y tenga una capa más de seguridad
    private String nombre;
    private String apellidos;


    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id_cliente=" + id_cliente +
                ", estado=" + estado +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}
