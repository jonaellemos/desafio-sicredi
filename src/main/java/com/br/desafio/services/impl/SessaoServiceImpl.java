package com.br.desafio.services.impl;

import com.br.desafio.entity.Pauta;
import com.br.desafio.entity.Sessao;
import com.br.desafio.exceptions.ServiceException;
import com.br.desafio.repository.PautaRepository;
import com.br.desafio.repository.SessaoRespository;
import com.br.desafio.services.SessaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class SessaoServiceImpl implements SessaoService {

    private static final Logger log = LoggerFactory.getLogger(SessaoServiceImpl.class);

    private final SessaoRespository repository;
    private final PautaRepository pautaRepository;

    public SessaoServiceImpl(SessaoRespository repository, PautaRepository pautaRepository) {
        this.repository = repository;
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Sessao criarSessao(Sessao sessao) {
        log.info("Iniciando criação da Sessão em: " + LocalDateTime.now() + "Para a pauta: " + sessao.getIdPauta());
        validar(sessao);
        sessao.setDataAbertura(LocalDateTime.now());
        sessao.gerarParametros(sessao);
        repository.save(sessao);
        return sessao;
    }

    @Override
    public Sessao buscarSessaoPorId(Long id) {
        return null;
    }


    private void validar(Sessao sessao){
        Optional<Pauta> pauta =  pautaRepository.findById(sessao.getIdPauta());

        if(pauta.isEmpty()){
            throw new ServiceException("Não foi encontrada Pauta com o id informado, por gentileza verificar. ");
        }

        if(sessao.getDataFechamento() != null){
            throw new ServiceException("A Pauta encontra-se encerrada, não sendo permitida abertura de novas sessões. ");
        }

    }
}
