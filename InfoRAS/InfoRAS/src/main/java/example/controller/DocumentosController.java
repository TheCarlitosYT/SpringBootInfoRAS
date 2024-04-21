package example.controller;

import example.domain.Documentos;
import example.domain.TipoEvento;
import example.dto.DocumentosDTO;
import example.exception.EventoNotFoundException;
import example.mapper.DocumentoMapper;
import example.service.DocumentoService;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static example.controller.Response.NOT_FOUND;
//TODO Esto es documentos, debo cambiarlo.
//TODO PORFA HACER ESTO YA :D
@RestController
public class DocumentosController {
    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private DocumentoMapper documentoMapper;

    @Operation(summary = "Obtiene el listado de documentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de documentos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Documentos.class)))),
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @GetMapping(value = "/documentos", produces = "application/json")
    public ResponseEntity<Set<DocumentosDTO>> getDocumentos(@RequestParam(value = "nombre", defaultValue = "") String nombre) {
        Set<DocumentosDTO> documentoDTO;
        if (nombre.isEmpty()) {
            documentoDTO = documentoService.findAll();
        } else {
            documentoDTO = documentoService.findByTitulo(nombre);
        }
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un evento determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el evento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ADMIN_ROLE', 'USER_ROLE')")
    @GetMapping(value = "/documentos/{id}", produces = "application/json")
    public ResponseEntity<Optional<DocumentosDTO>> getEventoById(@PathVariable long id) {
        Optional<DocumentosDTO> documentoDTO = documentoService.findById(id);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene un evento por titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el username", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El username no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @GetMapping(value = "/documentos/titulo/{titulo}", produces = "application/json")
    public ResponseEntity<Set<DocumentosDTO>> getEventoByTitulo(@PathVariable String titulo) {
        Set<DocumentosDTO> documentoDTO = documentoService.findByTitulo(titulo);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un evento por fecha y hora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el evento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @GetMapping(value = "/documentos/username/{username}", produces = "application/json")
    public ResponseEntity<Set<DocumentosDTO>> getEventoByUsername(@PathVariable Date fecha) {
        Set<DocumentosDTO> documentoDTO = documentoService.findByFecha(fecha);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un evento por tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el evento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @GetMapping(value = "/documentos/username/{username}", produces = "application/json")
    public ResponseEntity<Set<DocumentosDTO>> getEventoBy(@PathVariable TipoEvento tipo) {
        Set<DocumentosDTO> documentoDTO = documentoService.findByTipoEvento(tipo);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registró el evento", content = @Content(schema = @Schema(implementation = Documentos.class)))
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PostMapping(value = "/documentos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<DocumentosDTO> addEvento(@RequestBody DocumentosDTO documentoDTO) {
        DocumentosDTO addedEventoDTO = documentoService.addEvento(documentoDTO);
        return new ResponseEntity<>(addedEventoDTO, HttpStatus.OK);
    }


    @Operation(summary = "Modifica los datos de un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modificó el evento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PutMapping(value = "/documentos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<DocumentosDTO> modifyEvento(@PathVariable long id, @RequestBody DocumentosDTO newEventoDTO) {
        DocumentosDTO documentoDTO = documentoService.modifyEvento(id, newEventoDTO);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó el evento", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @DeleteMapping(value = "/documentos/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteEvento(@PathVariable long id)
    {
        documentoService.deleteEvento(id);
        return new ResponseEntity<>(Response.noErrorResponse(),
                HttpStatus.OK);
    }

    @ExceptionHandler(EventoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response>
    handleException(EventoNotFoundException cnfe) {
        Response response = Response.errorResonse(NOT_FOUND,
                cnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
