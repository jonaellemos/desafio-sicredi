package com.br.desafio.mapper;

import com.br.desafio.domain.SessaoDTO;
import com.br.desafio.entity.Sessao;

public class SessaoMapper {

    public static Sessao fromDTO(SessaoDTO sessaoDTO) {
        return Sessao.builder()
                .id(sessaoDTO.getId())
                .idPauta(sessaoDTO.getIdPauta())
                .minuto(sessaoDTO.getMinuto())
                .build();
    }

    public static SessaoDTO fromEntity(Sessao sessao) {
        return SessaoDTO.builder()
                .id(sessao.getId())
                .idPauta(sessao.getIdPauta())
                .dataLimite(sessao.getDataLimite())
                .dataAbertura(sessao.getDataAbertura())
                .dataFechamento(sessao.getDataFechamento())
                .integracaoMensageiro(sessao.isIntegracaoMensageiro())
                .status(sessao.getStatus())
                .build();
    }

}
