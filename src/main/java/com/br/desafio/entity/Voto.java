package com.br.desafio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "VOTO")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"USUARIO_ID", "idSessao"})})
@Getter
@Setter
public class Voto {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String reciboVoto;

    private String voto;

    @OneToOne
    private Usuario usuario;

    private Long idSessao;

    private Long idPauta;

    private LocalDateTime dataVoto;

    public Voto gerarProtocolo(Voto voto){
        this.dataVoto = LocalDateTime.now();
        this.reciboVoto = UUID.randomUUID() + voto.getUsuario().getCpf() + LocalDateTime.now();
        return voto;
    }

}
