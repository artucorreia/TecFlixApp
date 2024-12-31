package br.com.tecflix_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.controller.contract.IController;
import br.com.tecflix_app.data.DTO.v1.create.CreateModuleDTO;
import br.com.tecflix_app.data.DTO.v1.response.GenericResponseDTO;
import br.com.tecflix_app.data.DTO.v1.response.ModuleDTO;
import br.com.tecflix_app.service.ModuleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/modules")
public class ModuleController implements IController<ModuleDTO, Long> {

    private final ModuleService service;
    
    @Autowired
    public ModuleController(ModuleService service) { this.service = service; }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE 
    )
    public ResponseEntity<ModuleDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ModuleDTO>> findAll() {
        return ResponseEntity.ok(service.findByAll());
    }

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GenericResponseDTO<Long>> create(@Valid @RequestBody CreateModuleDTO data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
    }
}
