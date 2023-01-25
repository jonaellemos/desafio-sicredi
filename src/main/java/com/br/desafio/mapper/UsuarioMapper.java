package com.br.desafio.mapper;

import com.br.desafio.domain.UsuarioDTO;
import com.br.desafio.entity.Usuario;

public class UsuarioMapper {

    public static Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nome(usuarioDTO.getNome())
                .cpf(usuarioDTO.getCpf())
                .email(usuarioDTO.getEmail())
                .build();
    }

    public static UsuarioDTO fromEntity(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .cpf(usuario.getCpf())
                .email(usuario.getEmail())
                .build();
    }

}
