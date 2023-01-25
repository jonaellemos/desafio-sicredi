package com.br.desafio.services;

import com.br.desafio.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Usuario criarUsuario(Usuario fromDTo);

    Usuario buscarUsuarioPorCpf(String cpfUsuario);

}
