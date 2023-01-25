package com.br.desafio.repository;

import com.br.desafio.entity.Usuario;
import com.br.desafio.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotoRepository extends JpaRepository<Voto, Long> {


    Voto findVotoByIdSessaoAndUsuario(Long sessao, Usuario usuario);

}
