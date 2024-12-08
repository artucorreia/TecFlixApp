package br.com.tecflix_app.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tecflix_app.data.DTO.v1.create.PixChargeRequest;
import br.com.tecflix_app.service.payment.Pix;

@RestController
@RequestMapping("/api/v1/payments/pix")
public class PaymentController {
    
    private final Pix pixService;
    
    @Autowired
    public PaymentController(Pix pixService) {
        this.pixService = pixService;
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPixKey() {
        JSONObject response = pixService.getPixKey();
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response.toString());
    }
    
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> createCharge(@RequestBody PixChargeRequest pixChargeRequest) {
        JSONObject response = pixService.createCharge(pixChargeRequest);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response.toString());
    }
}
