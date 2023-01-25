package com.br.desafio.mapper;

import com.br.desafio.domain.PautaDTO;
import com.br.desafio.entity.Pauta;

public class PautaMapper {

    public static Pauta fromDTO(PautaDTO pautaDTO) {
        return Pauta.builder()
                .id(pautaDTO.getId())
                .status(pautaDTO.getStatus())
                .titulo(pautaDTO.getTitulo())
                .dataAbertura(pautaDTO.getDataAbertura())
                .dataFechamento(pautaDTO.getDataFechamento())
                .build();
    }

    public static PautaDTO fromEntity(Pauta pauta) {
        return PautaDTO.builder()
                .id(pauta.getId())
                .status(pauta.getStatus())
                .titulo(pauta.getTitulo())
                .dataAbertura(pauta.getDataAbertura())
                .dataFechamento(pauta.getDataFechamento())
                .sessoes(pauta.getSessoes())
                .usuarios(pauta.getUsuarios())
                .sessoes(pauta.getSessoes())
                .build();
    }

}
