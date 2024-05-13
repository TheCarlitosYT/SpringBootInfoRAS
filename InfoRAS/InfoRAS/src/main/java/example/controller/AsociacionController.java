package example.controller;
import example.domain.Asociacion;
import example.domain.Documentos;
import example.domain.Eventos;
import example.dto.AsociacionDTO;
import example.exception.AsociacionNotFoundException;
import example.service.AsociacionService;
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
public class AsociacionController {
    @Autowired
    private AsociacionService asociacionService;



    @Operation(summary = "Obtiene el listado de asociaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de asociaciones",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Asociacion.class)))),
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/asociaciones", produces = "application/json")
    public ResponseEntity<Set<AsociacionDTO>> getAsociacion(@RequestParam(value = "titulo", defaultValue = "") String nombre) {
        Set<AsociacionDTO> asociacionDTO;
        if (nombre.isEmpty()) {
            asociacionDTO = asociacionService.findAll();
        } else {
            asociacionDTO = asociacionService.findByNombre(nombre);
        }
        return new ResponseEntity<>(asociacionDTO, HttpStatus.OK);
    }

    
    @Operation(summary = "Obtiene una asociacion determinada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe la asociacion", content = @Content(schema = @Schema(implementation = Asociacion.class))),
            @ApiResponse(responseCode = "404", description = "La asociacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/asociaciones/{id}", produces = "application/json")
    public ResponseEntity<Optional<AsociacionDTO>> getAsociacionById(@PathVariable long id) {
        Optional<AsociacionDTO> asociacionDTO = asociacionService.findById(id);
        return new ResponseEntity<>(asociacionDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los documentos de una asociacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvieron los documentos de la asociacion", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Documentos.class)))),
            @ApiResponse(responseCode = "404", description = "El documento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/asociaciones/{id}/documentos", produces = "application/json")
    public ResponseEntity<List<Documentos>> getDocumentosByAsociacionId(@PathVariable Long id) {
        List<Documentos> documentos = asociacionService.findDocumentosByAsociacionId(id);
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los eventos de una asociacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvieron los eventos de la asociacion", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Eventos.class)))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/asociaciones/{id}/eventos", produces = "application/json")
    public ResponseEntity<List<Eventos>> getEventosByAsociacionId(@PathVariable Long id) {
        List<Eventos> eventos = asociacionService.findEventosByAsociacionId(id);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una asociacion determinada por titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el username", content = @Content(schema = @Schema(implementation = Asociacion.class))),
            @ApiResponse(responseCode = "404", description = "El username no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/asociaciones/nombre/{nombre}", produces = "application/json")
    public ResponseEntity<Set<AsociacionDTO>> getAsociacionByTitulo(@PathVariable String nombre) {
        Set<AsociacionDTO> asociacionDTO = asociacionService.findByNombre(nombre);
        return new ResponseEntity<>(asociacionDTO, HttpStatus.OK);
    }
    //TODO
    @Operation(summary = "Registra una nueva asociacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registró la asociacion", content = @Content(schema = @Schema(implementation = Asociacion.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/asociaciones", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AsociacionDTO> addAsociacion(@RequestBody AsociacionDTO asociacionDTO) {
        AsociacionDTO addedAsociacionDTO = asociacionService.addAsociacion(asociacionDTO);
        return new ResponseEntity<>(addedAsociacionDTO, HttpStatus.OK);
    }

    @Operation(summary = "Modifica los datos de una asociacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modificó la asociacion", content = @Content(schema = @Schema(implementation = Asociacion.class))),
            @ApiResponse(responseCode = "404", description = "La asociacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/asociaciones/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<AsociacionDTO> modifyAsociacion(@PathVariable long id, @RequestBody AsociacionDTO newAsociacionDTO) {
        AsociacionDTO asociacionDTO = asociacionService.modifyAsociacion(id, newAsociacionDTO);
        return new ResponseEntity<>(asociacionDTO, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una asociacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó la asociacion", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "La asociacion no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/asociaciones/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteAsociacion(@PathVariable long id)
    {
        asociacionService.deleteAsociacion(id);
        return new ResponseEntity<>(Response.noErrorResponse(),
                HttpStatus.OK);
    }

    @ExceptionHandler(AsociacionNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response>
    handleException(AsociacionNotFoundException anfe) {
        Response response = Response.errorResonse(NOT_FOUND,
                anfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
