package com.br.desafio.schedule;


import com.br.desafio.entity.Pauta;
import com.br.desafio.entity.Sessao;
import com.br.desafio.entity.SumarioResultadoVotacao;
import com.br.desafio.exceptions.ServiceException;
import com.br.desafio.repository.PautaRepository;
import com.br.desafio.repository.SessaoRespository;
import com.br.desafio.repository.SumarioResultadoVotacaoRepository;
import com.br.desafio.services.MensageiroService;
import com.br.desafio.services.SumarioResultadoVotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.br.desafio.config.Constantes.ABERTA;
import static com.br.desafio.config.Constantes.ENCERRADA;

@Component
@EnableScheduling
public class SumarioSchedule {
    private static final Logger logger = LoggerFactory.getLogger(SumarioSchedule.class);

    private final PautaRepository pautaRepository;
    private final SumarioResultadoVotacaoService sumarioResultadoVotacaoService;
    private final SumarioResultadoVotacaoRepository sumarioResultadoVotacaoRepository;
    private final MensageiroService mensageiroService;
    private final SessaoRespository sessaoRespository;

    public SumarioSchedule(PautaRepository pautaRepository,
                           SumarioResultadoVotacaoService sumarioResultadoVotacaoService,
                           SumarioResultadoVotacaoRepository sumarioResultadoVotacaoRepository,
                           MensageiroService mensageiroService, SessaoRespository sessaoRespository) {
        this.pautaRepository = pautaRepository;
        this.sumarioResultadoVotacaoService = sumarioResultadoVotacaoService;
        this.sumarioResultadoVotacaoRepository = sumarioResultadoVotacaoRepository;
        this.mensageiroService = mensageiroService;
        this.sessaoRespository = sessaoRespository;
    }

    @Scheduled(fixedDelay = 10000)
    public void gerarSumarioAutomatico(){
        List<Pauta> listaPautasParaEncerrar = new LinkedList<>();

        List<Pauta> pautasEmAberto = pautaRepository.findAllByStatus(ABERTA);

                pautasEmAberto.stream().forEach((pauta -> {
                    List<Sessao> sessao = sessaoRespository.findByIdPauta(pauta.getId());

                    //Comparando as sessões da Pauta em aberto com a data limite igual a data/hora ataul;
                    sessao.stream().forEach((sessoes -> {
                        String horaLimiteFormatada = sessoes.getDataLimite().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                        String dataAtual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

                        logger.info("Buscando Sessões com encerramento no minuto vigente.");
                        if (horaLimiteFormatada.equals(dataAtual)){
                            listaPautasParaEncerrar.add(pauta);
                        }
                    }));


                }));

                listaPautasParaEncerrar.stream().forEach((pauta -> {
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
                    try {
                        mensageiroService.enviarMensagem(message.toString());
                        encerrarPautaeSessoes(Optional.ofNullable(x.getPauta()));
                    }catch (Exception e){
                        throw new ServiceException("Não foi possível enviar mensagem para o Servidor do Mensageiro");
                    }
                }));
            } catch (Exception e) {
                throw new ServiceException("Não foi possível conectar com o Servidor do Mensageiro");
            }
        }

    }


    public void encerrarPautaeSessoes(Optional<Pauta> pauta){
        logger.info("Encerrando Sessões e Pauta.");
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
