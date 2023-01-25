package com.br.desafio.controllers;

import com.br.desafio.domain.SessaoDTO;
import com.br.desafio.entity.Sessao;
import com.br.desafio.mapper.SessaoMapper;
import com.br.desafio.services.SessaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.br.desafio.mapper.SessaoMapper.fromEntity;

@RestController
@RequestMapping("/sessoes")
@Validated
public class SessaoController {

    private final SessaoService service;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.service = sessaoService;
    }

    @PostMapping
    public ResponseEntity<Object> criarPauta(@Valid @RequestBody SessaoDTO sessaoDTO) {
        Sessao novaSessao = service.criarSessao(SessaoMapper.fromDTO(sessaoDTO));
        return new ResponseEntity<>(fromEntity(novaSessao), HttpStatus.CREATED);
    }

}
