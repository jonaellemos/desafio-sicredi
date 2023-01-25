package com.br.desafio.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CpfIntegracaoDTO implements Serializable {
    @JsonProperty("status")
    private String status;
}