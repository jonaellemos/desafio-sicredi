package com.br.desafio.services.impl;

import com.br.desafio.domain.CpfIntegracaoDTO;
import com.br.desafio.entity.Sessao;
import com.br.desafio.entity.Voto;
import com.br.desafio.exceptions.ServiceException;
import com.br.desafio.repository.SessaoRespository;
import com.br.desafio.repository.VotoRepository;
import com.br.desafio.services.VotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.br.desafio.config.Constantes.*;

@Component
public class VotoServiceImpl implements VotoService {

    private VotoRepository votoRepository;

    private SessaoRespository sessaoRespository;

    private final RestTemplate restTemplate;

    @Value("${cpf.integracao.url}")
    private String url;

    public VotoServiceImpl(VotoRepository votoRepository, RestTemplate restTemplate, SessaoRespository sessaoRespository) {
        this.votoRepository = votoRepository;
        this.restTemplate = restTemplate;
        this.sessaoRespository = sessaoRespository;
    }

    @Override
    @Transactional
    public Voto votar(Voto voto) {
        Voto votovalido = validarVoto(voto);
        votoRepository.save(votovalido);
        return votovalido;
    }

    @Override
    public CpfIntegracaoDTO votarIntegracao(Voto voto) {
        return verificarIntegracao(voto);
    }

    private Voto validarVoto(Voto voto){
        Optional<Sessao> dadosSessao = sessaoRespository.findById(voto.getIdSessao());

        if(!(voto.getVoto().equals(SIM) || voto.getVoto().equals(NAO))){
            throw new ServiceException(VOTO_INVALIDO_EXCEPTION);
        }

        Voto votoResult = votoRepository.findVotoByIdPautaAndUsuario(dadosSessao.get().getIdPauta(), voto.getUsuario());
        if(votoResult != null){
            throw new ServiceException("O usuário já votou para esta Pauta. ");
        }

        voto.gerarProtocolo(voto);


        voto.setIdPauta(dadosSessao.get().getIdPauta());

        return voto;
    }

    private CpfIntegracaoDTO verificarIntegracao(Voto voto) {
        CpfIntegracaoDTO cpfIntegracaoDTO = restTemplate.getForObject(url + voto.getUsuario().getCpf(), CpfIntegracaoDTO.class);
        if (cpfIntegracaoDTO.getStatus().equals(CPF_APTO)) {
            return cpfIntegracaoDTO;
        } else{
            cpfIntegracaoDTO.setStatus(CPF_INAPTO);
            return cpfIntegracaoDTO;
        }
    }

}
