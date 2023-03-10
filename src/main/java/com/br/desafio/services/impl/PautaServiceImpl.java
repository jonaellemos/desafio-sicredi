package com.br.desafio.services.impl;

import com.br.desafio.domain.PautaDTO;
import com.br.desafio.domain.SessaoDTO;
import com.br.desafio.entity.Pauta;
import com.br.desafio.repository.PautaRepository;
import com.br.desafio.services.PautaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.br.desafio.config.Constantes.ABERTA;

@Component
public class PautaServiceImpl implements PautaService {

    private static final Logger log = LoggerFactory.getLogger(PautaServiceImpl.class);

    private final PautaRepository pautaRepository;

    public PautaServiceImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta criarPauta(Pauta pauta) {
        log.info("Iniciando criação da pauta " + pauta.getTitulo() + " em: " + LocalDateTime.now());
        pauta.setDataAbertura(new Date());
        pauta.setStatus(ABERTA);
        pautaRepository.save(pauta);
        return pauta;
    }

    @Override
    public Pauta buscarPautaPorId(Long id) {
        return null;
    }

    @Override
    public List<Pauta> buscarPautaPorFiltro(PautaDTO pautaDTO) {
        return null;
    }

    @Override
    public List<Pauta> buscarPautas(boolean filtro) {
        return null;
    }

    @Override
    public Pauta atualizarStatusPauta(PautaDTO pautaDTO) {
        return null;
    }
}
