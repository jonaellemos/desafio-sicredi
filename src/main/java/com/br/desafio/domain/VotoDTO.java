package com.br.desafio.domain;

import com.br.desafio.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VotoDTO {

    private Long id;

    private String reciboVoto;

    private String voto;

    private Usuario usuario;

    private Long idSessao;

    private Long idPauta;

    private LocalDateTime dataVoto;

}
