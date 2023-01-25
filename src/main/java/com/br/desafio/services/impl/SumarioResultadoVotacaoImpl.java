package com.br.desafio.services.impl;

import com.br.desafio.entity.Pauta;
import com.br.desafio.entity.Sessao;
import com.br.desafio.entity.SumarioResultadoVotacao;
import com.br.desafio.entity.Voto;
import com.br.desafio.repository.PautaRepository;
import com.br.desafio.repository.SessaoRespository;
import com.br.desafio.repository.SumarioResultadoVotacaoRepository;
import com.br.desafio.repository.VotoRepository;
import com.br.desafio.services.SumarioResultadoVotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.br.desafio.config.Constantes.*;

@Component
public class SumarioResultadoVotacaoImpl implements SumarioResultadoVotacaoService {

    private static final Logger log = LoggerFactory.getLogger(SumarioResultadoVotacaoImpl.class);

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoRespository sessaoRespository;

    @Autowired
    VotoRepository votoRepository;

    @Autowired
    SumarioResultadoVotacaoRepository repository;


    @Override
    public SumarioResultadoVotacao criarSumario(Long idPauta) {

        Optional<Pauta> dadosPauta = pautaRepository.findById(idPauta);

        SumarioResultadoVotacao sumarioResultadoVotacao = totalizarVotos(dadosPauta);

        return sumarioResultadoVotacao;
    }

    @Override
    public Optional<SumarioResultadoVotacao> buscarSumarioPorId(Long idPauta) {
        return repository.findById(idPauta);
    }

    private SumarioResultadoVotacao totalizarVotos(Optional<Pauta> pauta){

        SumarioResultadoVotacao resultadoVotacao = new SumarioResultadoVotacao();

        List<Voto> votosValidos = votoRepository.findVotoByIdPauta(pauta.get().getId());

        Integer quantidadeSim = obterVotos(votosValidos, SIM);
        Integer quantidadeNao = obterVotos(votosValidos, NAO);

        resultadoVotacao.setVotoSim(quantidadeSim);
        resultadoVotacao.setVotoNao(quantidadeNao);
        resultadoVotacao.setPauta(pauta.get());
        resultadoVotacao.calcularResultadoFinal(quantidadeSim, quantidadeNao);

        repository.save(resultadoVotacao);
        return resultadoVotacao;
    }

    private Integer obterVotos(List<Voto> votos, String opcao) {
        List<Voto> votosFiltrados = votos.stream().filter(voto -> opcao.equals(voto.getVoto())).collect(Collectors.toList());
        return votosFiltrados.size();
    }

    public void encerrarPautaeSessoes(Optional<Pauta> pauta){
        log.info("Encerrando Sessões e Pauta.");
        pauta.get().setDataFechamento(new Date());
        pauta.get().setStatus(ENCERRADA);
        pautaRepository.save(pauta.get());

        for (Sessao sessao : pauta.get().getSessoes()) {
            sessao.setStatus(ENCERRADA);
            sessao.setDataFechamento(LocalDateTime.now());
            sessaoRespository.save(sessao);
        }

    }
}
