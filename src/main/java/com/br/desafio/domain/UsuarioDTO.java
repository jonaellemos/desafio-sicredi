package com.br.desafio.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O nome não pode ficar em branco.")
    private String nome;

    @Email(message = "Forneça um e-mail válido. ")
    @NotBlank(message = "O email não pode ficar em branco.")
    private String email;

    @NotBlank(message = "O cpf deve ser obrigatoriamente informado.")
    @CPF(message = "Forneça um cpf válido para esta operação. ")
    private String cpf;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

}
