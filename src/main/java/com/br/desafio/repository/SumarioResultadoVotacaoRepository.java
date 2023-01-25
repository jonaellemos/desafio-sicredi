package com.br.desafio.repository;

import com.br.desafio.entity.SumarioResultadoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SumarioResultadoVotacaoRepository extends JpaRepository<SumarioResultadoVotacao, Long> {
    List<SumarioResultadoVotacao> findByIntegradoComMensageiro(Boolean status);
}
