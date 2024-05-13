package example.controller;

import example.domain.Documentos;
import example.domain.TipoDoc;
import example.dto.DocumentosDTO;
import example.exception.DocumentoNotFoundException;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
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

    @Operation(summary = "Obtiene un documento determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el documento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El documento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/documentos/{id}", produces = "application/json")
    public ResponseEntity<Optional<DocumentosDTO>> getDocumentoById(@PathVariable long id) {
        Optional<DocumentosDTO> documentoDTO = documentoService.findById(id);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene un documento por titulo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el documento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El documento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/documentos/titulo/{titulo}", produces = "application/json")
    public ResponseEntity<Set<DocumentosDTO>> getDocumentoByTitulo(@PathVariable String titulo) {
        Set<DocumentosDTO> documentoDTO = documentoService.findByTitulo(titulo);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un documento por tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el documento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El documento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/documentos/documento/{documento}", produces = "application/json")
    public ResponseEntity<Set<DocumentosDTO>> getDocumentoByTipoDocumentacion(@PathVariable TipoDoc tipo) {
        Set<DocumentosDTO> documentoDTO = documentoService.findByTipoDocumentacion(tipo);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se registró el documento", content = @Content(schema = @Schema(implementation = Documentos.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/documentos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<DocumentosDTO> addDocumento(@RequestBody DocumentosDTO documentoDTO) {
        DocumentosDTO addedDocumentoDTO = documentoService.addDocumento(documentoDTO);
        return new ResponseEntity<>(addedDocumentoDTO, HttpStatus.OK);
    }


    @Operation(summary = "Modifica los datos de un documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modificó el documento", content = @Content(schema = @Schema(implementation = Documentos.class))),
            @ApiResponse(responseCode = "404", description = "El documento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/documentos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<DocumentosDTO> modifyDocumento(@PathVariable long id, @RequestBody DocumentosDTO newDocumentoDTO) {
        DocumentosDTO documentoDTO = documentoService.modifyDocumento(id, newDocumentoDTO);
        return new ResponseEntity<>(documentoDTO, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se eliminó el documento", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El documento no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/documentos/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteDocumento(@PathVariable long id)
    {
        documentoService.deleteDocumento(id);
        return new ResponseEntity<>(Response.noErrorResponse(),
                HttpStatus.OK);
    }

    @ExceptionHandler(DocumentoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response>
    handleException(DocumentoNotFoundException cnfe) {
        Response response = Response.errorResonse(NOT_FOUND,
                cnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
