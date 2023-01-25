package com.br.desafio.repository;

import com.br.desafio.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

    @Override
    List<Pauta> findAll();

    List<Pauta> findAllByStatus(String status);

}
