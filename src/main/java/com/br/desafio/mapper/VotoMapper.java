package com.br.desafio.mapper;

import com.br.desafio.domain.VotoDTO;
import com.br.desafio.entity.Voto;

public class VotoMapper {

    public static Voto fromDTO(VotoDTO votoDTO){
        return Voto.builder()
                .voto(votoDTO.getVoto())
                .idSessao(votoDTO.getIdSessao())
                .usuario(votoDTO.getUsuario())
                .build();
    }

    public static VotoDTO fromEntity(Voto voto){
        return VotoDTO.builder()
                .id(voto.getId())
                .reciboVoto(voto.getReciboVoto())
                .dataVoto(voto.getDataVoto())
                .voto(voto.getVoto())
                .usuario(voto.getUsuario())
                .build();
    }

}
