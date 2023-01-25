package com.br.desafio.domain;

import com.br.desafio.entity.Pauta;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SumarioResultadoVotacaoDTO {

    private Long id;

    @NotBlank(message = "Por gentileza enviar o id da pauta para que seja gerada a totalização. ")
    private Long idPauta;

    private Pauta pauta;

    private Integer votoSim;

    private Integer votoNao;

    private String resultadoFinal;

    private boolean integradoComMensageiro;

}
