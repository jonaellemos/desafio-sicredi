package com.br.desafio.domain;

import com.br.desafio.entity.Sessao;
import com.br.desafio.entity.Usuario;
import com.br.desafio.entity.Voto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PautaDTO implements Serializable {

    @JsonProperty("idPauta")
    private Long id;

    @JsonProperty("titulo")
    @Size(min = 5, message = "O título deve conter no mínimo 5 caracteres. ")
    @NotBlank(message = "o titulo não pode ser nulo.")
    private String titulo;

    private List<Voto> votos;
    private List<Sessao> sessoes;
    private List<Usuario> usuarios;

    @JsonProperty("status")
    private String status;

    private Date dataAbertura;

    private Date dataFechamento;

    @Override
    public String toString() {
        return "PautaDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", votos=" + votos +
                ", sessoes=" + sessoes +
                ", usuarios=" + usuarios +
                ", status='" + status + '\'' +
                ", dataAbertura=" + dataAbertura +
                ", dataFechamento=" + dataFechamento +
                '}';
    }
}
