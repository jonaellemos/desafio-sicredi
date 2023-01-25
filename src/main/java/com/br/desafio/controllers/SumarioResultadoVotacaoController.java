package com.br.desafio.controllers;

import com.br.desafio.domain.PautaDTO;
import com.br.desafio.domain.SumarioResultadoVotacaoDTO;
import com.br.desafio.entity.Pauta;
import com.br.desafio.entity.SumarioResultadoVotacao;
import com.br.desafio.mapper.PautaMapper;
import com.br.desafio.mapper.SumarioResultadoVotacaoMapper;
import com.br.desafio.services.PautaService;
import com.br.desafio.services.SumarioResultadoVotacaoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.br.desafio.mapper.SumarioResultadoVotacaoMapper.fromEntity;

@RestController
@RequestMapping("/resultadoVotacao")
@Validated
public class SumarioResultadoVotacaoController {

    private final SumarioResultadoVotacaoService service;

    public SumarioResultadoVotacaoController(SumarioResultadoVotacaoService service) {
        this.service = service;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Object> buscarSumario(@Valid @PathVariable Long id){
        SumarioResultadoVotacao resultadoVotacao = service.criarSumario(id);
        return new ResponseEntity<>(fromEntity(resultadoVotacao), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> buscarSumarioPorId(@Valid @PathVariable Long id){
        SumarioResultadoVotacao resultadoVotacao = service.criarSumario(id);
        return new ResponseEntity<>(fromEntity(resultadoVotacao), HttpStatus.OK);
    }

}
