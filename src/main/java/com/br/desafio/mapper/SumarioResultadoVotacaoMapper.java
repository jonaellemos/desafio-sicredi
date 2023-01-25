package com.br.desafio.mapper;

import com.br.desafio.domain.PautaDTO;
import com.br.desafio.domain.SumarioResultadoVotacaoDTO;
import com.br.desafio.entity.Pauta;
import com.br.desafio.entity.SumarioResultadoVotacao;

public class SumarioResultadoVotacaoMapper {

    public static SumarioResultadoVotacaoDTO fromEntity(SumarioResultadoVotacao sumarioResultadoVotacao){
        return SumarioResultadoVotacaoDTO.builder()
                .id(sumarioResultadoVotacao.getId())
                .votoSim(sumarioResultadoVotacao.getVotoSim())
                .votoNao(sumarioResultadoVotacao.getVotoNao())
                .resultadoFinal(sumarioResultadoVotacao.getResultadoFinal())
                .build();
    }


}
