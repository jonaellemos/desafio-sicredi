package com.br.desafio.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "PAUTA")
@Getter
@Setter
public class Pauta {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "idPauta")
    private Long id;

    private String titulo;

    @OneToMany
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "idPauta", referencedColumnName = "idPauta")
    private List<Voto> votos;

    @OneToMany
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "idPauta", referencedColumnName = "idPauta")
    private List<Sessao> sessoes;

    @ManyToMany
    private List<Usuario> usuarios;

    private String status;

    private Date dataAbertura;

    private Date dataFechamento;


}
