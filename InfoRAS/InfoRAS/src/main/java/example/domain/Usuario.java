package example.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name="Usuario")
public class Usuario {

    @Schema(description = "Identificador del usuario", example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id_usuario;

    @Schema(description = "Estado del usuario", example = "ACTIVO", required = false)
    @Column(name = "estado")
    private EstadoUsuario estado;

    //email pero se transforma en el backend a username
    @Schema(description = "Email del usuario", example = "jose@gmail.com", required = true)
    @NotBlank
    @Column(name = "username")
    private String username;

    @Schema(description = "Contraseña del usuario", example = "123456", required = true)
    @NotBlank
    @Column(name = "password")
    private String password;

    @Schema(description = "Nombre del usuario", example = "Jose", required = true)
    @Column(name = "nombre")
    private String nombre;

    @Schema(description = "Apellidos del usuario", example = "Serrano", required = true)
    @Column(name = "apellidos")
    private String apellidos;
    

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private List<Eventos> eventos;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento_id")
    private List<Documentos> documentos;


    public Usuario() {
    }

    public Usuario(EstadoUsuario estado, String username, String password, String nombre, String apellidos) {
        this.estado = estado;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
        return "{" +
                "\"estado\":" + "\"" + estado + "\"" +
                ", \"username\":" + "\"" + username + "\"" +
                ", \"password\":" + "\"" + password + "\"" +
                ", \"nombre\":" + "\"" + nombre + "\"" +
                ", \"apellidos\":" + "\"" + apellidos + "\"" +
                ", \"roles\": [\"user\"]}";
    }
}
