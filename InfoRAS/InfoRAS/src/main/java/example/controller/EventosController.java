package example.controller;

import example.domain.Documentos;
import example.domain.Eventos;
import example.dto.EventosDTO;
import example.exception.EventoNotFoundException;
import example.mapper.EventoMapper;
import example.service.EventoService;
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
//TODO
@RestController
public class EventosController {
    @Autowired
    private EventoService eventoService;

    @Autowired
    private EventoMapper eventoMapper;

    @Operation(summary = "Obtiene el listado de eventos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de eventos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Eventos.class)))),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/eventos", produces = "application/json")
    public ResponseEntity<Set<EventosDTO>> getEventos(@RequestParam(value = "nombre", defaultValue = "") String nombre) {
        Set<EventosDTO> eventoDTO;
        if (nombre.isEmpty()) {
            eventoDTO = eventoService.findAll();
        } else {
            eventoDTO = eventoService.findByNombre(nombre);
        }
        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un evento determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el evento", content = @Content(schema = @Schema(implementation = Eventos.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/eventos/{id}", produces = "application/json")
    public ResponseEntity<Optional<EventosDTO>> getEventoById(@PathVariable long id) {
        Optional<EventosDTO> eventoDTO = eventoService.findById(id);
        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene un evento por titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el username", content = @Content(schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "404", description = "El username no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/eventos/username/{username}", produces = "application/json")
    public ResponseEntity<Optional<EventosDTO>> getEventoByUsername(@PathVariable String username) {
        Optional<EventosDTO> eventoDTO = eventoService.findByUsername(username);
        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un evento por fecha y hora")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el username", content = @Content(schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "404", description = "El username no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/eventos/username/{username}", produces = "application/json")
    public ResponseEntity<Optional<EventosDTO>> getEventoByUsername(@PathVariable String username) {
        Optional<EventosDTO> eventoDTO = eventoService.findByUsername(username);
        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registró el evento", content = @Content(schema = @Schema(implementation = Evento.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/eventos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<EventosDTO> addEvento(@RequestBody EventosDTO eventoDTO) {
        EventosDTO addedEventoDTO = eventoService.addEvento(eventoDTO);
        return new ResponseEntity<>(addedEventoDTO, HttpStatus.OK);
    }


    @Operation(summary = "Modifica los datos de un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modificó el evento", content = @Content(schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/eventos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<EventosDTO> modifyEvento(@PathVariable long id, @RequestBody EventosDTO newEventoDTO) {
        EventosDTO eventoDTO = eventoService.modifyEvento(id, newEventoDTO);
        return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó el evento", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El evento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/eventos/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteEvento(@PathVariable long id)
    {
        eventoService.deleteEvento(id);
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
