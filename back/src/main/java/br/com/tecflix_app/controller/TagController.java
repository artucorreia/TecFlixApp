package br.com.tecflix_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.data.DTO.v1.response.TagDTO;
import br.com.tecflix_app.service.TagService;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"})
@RestController
@RequestMapping("api/v1/tags")
public class TagController {

    private final TagService service;
    
    @Autowired
    public TagController(TagService service) { this.service = service; }

    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE 
    )
    public ResponseEntity<TagDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TagDTO>> findAll() {
        return ResponseEntity.ok(service.findByAll());
    }
}
