package com.br.desafio.repository;

import com.br.desafio.entity.Usuario;
import com.br.desafio.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    Voto findVotoByIdPautaAndUsuario(Long idPauta, Usuario usuario);

    List<Voto> findVotoByIdPauta(Long idpauta);

}
