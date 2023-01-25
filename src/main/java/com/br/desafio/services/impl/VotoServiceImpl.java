package com.br.desafio.services.impl;

import com.br.desafio.domain.CpfIntegracaoDTO;
import com.br.desafio.entity.Voto;
import com.br.desafio.exceptions.ServiceException;
import com.br.desafio.exceptions.VotoException;
import com.br.desafio.repository.VotoRepository;
import com.br.desafio.services.VotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static com.br.desafio.config.Constantes.*;

@Component
public class VotoServiceImpl implements VotoService {

    private VotoRepository votoRepository;

    private final RestTemplate restTemplate;

    @Value("${cpf.integracao.url}")
    private String url;

    public VotoServiceImpl(VotoRepository votoRepository, RestTemplate restTemplate) {
        this.votoRepository = votoRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Voto votar(Voto voto) {
        validarVoto(voto);
        voto.gerarProtocolo(voto);
        votoRepository.save(voto);
        return voto;
    }

    @Override
    public CpfIntegracaoDTO votarIntegracao(Voto voto) {
        return verificarIntegracao(voto);
    }

    private boolean validarVoto(Voto voto){

        if(!(voto.getVoto().equals(SIM) || voto.getVoto().equals(NAO))){
            throw new ServiceException(VOTO_INVALIDO_EXCEPTION);
        }

        Voto votoResult = votoRepository.findVotoByIdSessaoAndUsuario(voto.getIdSessao(), voto.getUsuario());
        if(votoResult != null){
            throw new RuntimeException("O usuário já realizou voto para esta sessão. ");
        }

        return true;
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
