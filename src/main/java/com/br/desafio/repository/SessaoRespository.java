package com.br.desafio.repository;

import com.br.desafio.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SessaoRespository extends JpaRepository<Sessao, Long> {

    List<Sessao> findByIdPauta(Long id);
}
