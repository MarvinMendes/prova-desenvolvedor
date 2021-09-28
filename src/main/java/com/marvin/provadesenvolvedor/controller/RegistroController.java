package com.marvin.provadesenvolvedor.controller;

import com.marvin.provadesenvolvedor.domain.dto.RequisicaoBuscaRegistroDTO;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoRegistroDTO;
import com.marvin.provadesenvolvedor.domain.exceptions.ParametroNaoValidoException;
import com.marvin.provadesenvolvedor.domain.exceptions.RegistroFoiExcluidoDaBaseDeDadosException;
import com.marvin.provadesenvolvedor.domain.exceptions.RegistroNaoEncontradoException;
import com.marvin.provadesenvolvedor.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(service.salvar(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoBuscaRegistroDTO>> buscarRegistro(@RequestBody RequisicaoBuscaRegistroDTO dto)
            throws RegistroNaoEncontradoException,
            RegistroFoiExcluidoDaBaseDeDadosException {
        return new ResponseEntity<>(service.buscar(dto), HttpStatus.ACCEPTED);
    }

    @PutMapping("/atualiza/{id}")
    public ResponseEntity<RequisicaoRegistroDTO> atualizaRegistro(@PathVariable Long id, @RequestBody RequisicaoRegistroDTO dto)
            throws RegistroFoiExcluidoDaBaseDeDadosException, ParametroNaoValidoException, RegistroNaoEncontradoException {
        return new ResponseEntity<>(service.atualizaRegistro(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletaRegistro(@PathVariable Long id) throws RegistroNaoEncontradoException {
        service.deletar(id);
    }

}
