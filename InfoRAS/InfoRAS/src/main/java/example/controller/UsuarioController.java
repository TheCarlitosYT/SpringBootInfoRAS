package example.controller;

import example.domain.Documentos;
import example.domain.Eventos;
import example.domain.Usuario;
import example.domain.EstadoUsuario;
import example.dto.UsuarioDTO;
import example.exception.UsuarioNotFoundException;
import example.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static example.controller.Response.NOT_FOUND;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtiene el listado de usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/usuarios", produces = "application/json")
    public ResponseEntity<Set<UsuarioDTO>> getUsuarios(@RequestParam(value = "nombre", defaultValue = "") String nombre) {
        Set<UsuarioDTO> usuarioDTO;
        if (nombre.isEmpty()) {
            usuarioDTO = usuarioService.findAll();
        } else {
            usuarioDTO = usuarioService.findByNombre(nombre);
        }
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/usuarios/{id}", produces = "application/json")
    public ResponseEntity<Optional<UsuarioDTO>> getUsuarioById(@PathVariable long id) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.findById(id);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las documentos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvieron las documentos del usuario", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Documentos.class)))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/usuarios/{id}/documentos", produces = "application/json")
    public ResponseEntity<List<Documentos>> getDocumentosByUsuarioId(@PathVariable Long id) {
        List<Documentos> documentos = usuarioService.findDocumentosByUsuarioId(id);
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los eventos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvieron los eventos del usuario", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Eventos.class)))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/usuarios/{id}/eventos", produces = "application/json")
    public ResponseEntity<List<Eventos>> getEventosByUsuarioId(@PathVariable Long id) {
        List<Eventos> eventos = usuarioService.findEventosByUsuarioId(id);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un username determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el username", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El username no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/usuarios/username/{username}", produces = "application/json")
    public ResponseEntity<Optional<UsuarioDTO>> getUsuarioByUsername(@PathVariable String username) {
        Optional<UsuarioDTO> usuarioDTO = usuarioService.findByUsername(username);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un usuario determinado dependiendo de su estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/usuarios/estado/{estado}", produces = "application/json")
    public ResponseEntity<Set<UsuarioDTO>> findByEstado(@PathVariable EstadoUsuario estado) {
        Set<UsuarioDTO> usuarioDTO = usuarioService.findByEstado(estado);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registró el usuario", content = @Content(schema = @Schema(implementation = Usuario.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/usuarios", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UsuarioDTO> addUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO addedUsuarioDTO = usuarioService.addUsuario(usuarioDTO);
        return new ResponseEntity<>(addedUsuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Modifica los datos del usuario loggeado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modificó el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(value = "/usuarios/{id}/logged", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UsuarioDTO> modifyUsuarioData(@PathVariable long id, @RequestBody UsuarioDTO newUsuarioDTO) {
        UsuarioDTO usuarioDTO = usuarioService.modifyUsuarioData(id, newUsuarioDTO);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Modifica los datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modificó el usuario", content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/usuarios/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<UsuarioDTO> modifyUsuario(@PathVariable long id, @RequestBody UsuarioDTO newUsuarioDTO) {
        UsuarioDTO usuarioDTO = usuarioService.modifyUsuario(id, newUsuarioDTO);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó el usuario", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El usuario no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/usuarios/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteUsuario(@PathVariable long id)
    {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>(Response.noErrorResponse(),
                HttpStatus.OK);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response>
    handleException(UsuarioNotFoundException cnfe) {
        Response response = Response.errorResonse(NOT_FOUND,
                cnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
