package com.marvin.provadesenvolvedor.controller;

import com.marvin.provadesenvolvedor.domain.dto.RequisicaoRegistroDTO;
import com.marvin.provadesenvolvedor.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/registro")
public class RegistroController {

    
    private RegistroService service;

    @Autowired
    public RegistroController(RegistroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RequisicaoRegistroDTO> salvar(@RequestBody RequisicaoRegistroDTO dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.OK);
    }

}
