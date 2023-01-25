package com.br.desafio.entity;

import com.br.desafio.domain.SessaoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

import static com.br.desafio.utils.ValidarCamposNulosOuVazios.camporVazioOuNulo;
import static jakarta.persistence.GenerationType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "SESSAO")
@Getter
@Setter
public class Sessao {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private Long idPauta;

    private LocalDateTime dataAbertura;

    private LocalDateTime dataFechamento;

    private LocalDateTime dataLimite;

    private int minuto;

    private String status;

    private boolean integracaoMensageiro;

    public LocalDateTime gerarParametros(Sessao sessao) {
        this.dataAbertura = LocalDateTime.now();
        this.status = "EM ABERTO";
        this.integracaoMensageiro = false;
        return this.dataLimite = obterTempoFinal(sessao);
    }

    private LocalDateTime obterTempoFinal(Sessao sessao) {
        if (sessao.getMinuto() == 0 || camporVazioOuNulo(sessao.getMinuto())) {
            return LocalDateTime.now().plusMinutes(1);
        } else {
            return LocalDateTime.now().plusMinutes(sessao.getMinuto());
        }
    }

}
