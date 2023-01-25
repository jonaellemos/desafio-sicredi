package com.br.desafio.schedule;


import com.br.desafio.entity.Pauta;
import com.br.desafio.entity.SumarioResultadoVotacao;
import com.br.desafio.exceptions.ServiceException;
import com.br.desafio.repository.PautaRepository;
import com.br.desafio.repository.SumarioResultadoVotacaoRepository;
import com.br.desafio.services.MensageiroService;
import com.br.desafio.services.PautaService;
import com.br.desafio.services.SessaoService;
import com.br.desafio.services.SumarioResultadoVotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.br.desafio.config.Constantes.ABERTA;

@Component
@EnableScheduling
public class SumarioSchedule {
    private static final Logger logger = LoggerFactory.getLogger(SumarioSchedule.class);

    private final PautaRepository pautaRepository;
    private final SumarioResultadoVotacaoService sumarioResultadoVotacaoService;
    private final SumarioResultadoVotacaoRepository sumarioResultadoVotacaoRepository;
    private final MensageiroService mensageiroService;

    public SumarioSchedule(PautaRepository pautaRepository,
                           SumarioResultadoVotacaoService sumarioResultadoVotacaoService,
                           SumarioResultadoVotacaoRepository sumarioResultadoVotacaoRepository,
                           MensageiroService mensageiroService) {
        this.pautaRepository = pautaRepository;
        this.sumarioResultadoVotacaoService = sumarioResultadoVotacaoService;
        this.sumarioResultadoVotacaoRepository = sumarioResultadoVotacaoRepository;
        this.mensageiroService = mensageiroService;
    }

    @Scheduled(fixedDelay = 100000)
    public void gerarSumarioAutomatico(){
            List<Pauta> pautasEmAberto = pautaRepository.findAllByStatus(ABERTA);
            pautasEmAberto.stream().forEach((pauta -> {
                sumarioResultadoVotacaoService.criarSumario(pauta.getId());
            }));

            gerarNotificacaoMensageiro();
    }

    private void gerarNotificacaoMensageiro(){
        List<SumarioResultadoVotacao> message = sumarioResultadoVotacaoRepository.findByIntegradoComMensageiro(false);

        if(!message.isEmpty()) {
            try {
                message.stream().forEach((x -> {
                    logger.info("Enviando para Mensageiro: " + message.toString());
                    mensageiroService.enviarMensagem(message.toString());
                }));
            } catch (Exception e) {
                throw new ServiceException("Não foi possível conectar com o Servidor do Mensageiro");
            }
        }

    }

}
