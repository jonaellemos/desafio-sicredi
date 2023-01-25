package com.br.desafio.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import static com.br.desafio.config.Constantes.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SUMARIO")
@Builder
public class SumarioResultadoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "idPauta", referencedColumnName = "idPauta")
    private Pauta pauta;

    private Integer votoSim;
    private Integer votoNao;
    private String resultadoFinal;
    private boolean integradoComMensageiro;

    public void calcularResultadoFinal(int votoSim, int votoNao){
        if(votoSim > votoNao){
           this.resultadoFinal = MAIORIA_SIM;
        }else if(votoNao > votoSim){
            this.resultadoFinal = MAIORIA_NAO;
        }else{
            this.resultadoFinal = EMPATE_VOTOS;
        }

    }

    @Override
    public String toString() {
        return "SumarioResultadoVotacao{" +
                "id=" + id +
                ", pauta=" + pauta +
                ", votoSim=" + votoSim +
                ", votoNao=" + votoNao +
                ", resultadoFinal='" + resultadoFinal + '\'' +
                ", integradoComMensageiro=" + integradoComMensageiro +
                '}';
    }
}
