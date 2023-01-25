package com.br.desafio.services;

import com.br.desafio.entity.SumarioResultadoVotacao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SumarioResultadoVotacaoService {

   SumarioResultadoVotacao criarSumario(Long idPauta);

   Optional<SumarioResultadoVotacao> buscarSumarioPorId(Long idPauta);
}
