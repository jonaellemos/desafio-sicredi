package com.br.desafio.services;

import com.br.desafio.domain.PautaDTO;
import com.br.desafio.domain.SessaoDTO;
import com.br.desafio.entity.Pauta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaService {

    Pauta criarPauta(Pauta fromDTO);

    Pauta buscarPautaPorId(Long id);

    List<Pauta> buscarPautaPorFiltro(PautaDTO pautaDTO);

    List<Pauta> buscarPautas(boolean filtro);

    Pauta atualizarStatusPauta(PautaDTO pautaDTO);


}
