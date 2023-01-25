package com.br.desafio.controllers;

import com.br.desafio.domain.CpfIntegracaoDTO;
import com.br.desafio.domain.VotoDTO;
import com.br.desafio.entity.Voto;
import com.br.desafio.mapper.VotoMapper;
import com.br.desafio.services.VotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.br.desafio.mapper.VotoMapper.fromEntity;


@RestController
@RequestMapping("/votos")
@Validated
public class VotoController {

    private final VotoService service;

    @Autowired
    public VotoController(VotoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> votar(@Valid @RequestBody VotoDTO votoDTO) {
        Voto novoVoto = service.votar(VotoMapper.fromDTO(votoDTO));
        return new ResponseEntity<>(fromEntity(novoVoto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/integracao")
    public ResponseEntity<Object> votarIntegracao(@Valid @RequestBody VotoDTO votoDTO) {
        CpfIntegracaoDTO integracaoDTO = service.votarIntegracao(VotoMapper.fromDTO(votoDTO));
        return new ResponseEntity<>(integracaoDTO, HttpStatus.OK);
    }

}
