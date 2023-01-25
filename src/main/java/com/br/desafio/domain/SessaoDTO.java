package com.br.desafio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessaoDTO implements Serializable {

    private Long id;

    private Long idPauta;

    @JsonProperty("dataAbertura")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataAbertura;

    @JsonProperty("dataFechamento")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataFechamento;

    @JsonProperty("dataLimite")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataLimite;

    private int minuto;

    private String status;

    private boolean integracaoMensageiro;

    @Override
    public String toString() {
        return "SessaoDTO{" +
                "id=" + id +
                ", idPauta=" + idPauta +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                ", dataLimite=" + dataLimite +
                ", minuto=" + minuto +
                ", status='" + status + '\'' +
                '}';
    }
}
