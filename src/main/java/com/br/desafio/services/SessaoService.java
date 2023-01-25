package com.br.desafio.services;

import com.br.desafio.domain.SessaoDTO;
import com.br.desafio.entity.Sessao;
import org.springframework.stereotype.Service;

@Service
public interface SessaoService {

    Sessao criarSessao(Sessao fromDTO);

    Sessao buscarSessaoPorId(Long id);
}
