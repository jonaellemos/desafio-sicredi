package com.br.desafio.controllers;

import com.br.desafio.domain.PautaDTO;
import com.br.desafio.entity.Pauta;
import com.br.desafio.mapper.PautaMapper;
import com.br.desafio.services.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.br.desafio.mapper.PautaMapper.fromEntity;

@RestController
@RequestMapping("/pautas")
@Validated
public class PautaController {

   private final PautaService service;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.service = pautaService;
    }

    @PostMapping
    public ResponseEntity<Object> criarPauta(@RequestBody @Valid PautaDTO pautaDTO) {
        Pauta dadosCadastro = service.criarPauta(PautaMapper.fromDTO(pautaDTO));
        return new ResponseEntity<>(fromEntity(dadosCadastro), HttpStatus.CREATED);
    }

}
