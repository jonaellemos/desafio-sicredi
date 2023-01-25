package com.br.desafio.repository;

import com.br.desafio.entity.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SessaoRespository extends JpaRepository<Sessao, Long> {

}
