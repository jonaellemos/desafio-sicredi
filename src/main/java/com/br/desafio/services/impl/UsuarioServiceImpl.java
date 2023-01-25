package com.br.desafio.services.impl;

import com.br.desafio.domain.CpfIntegracaoDTO;
import com.br.desafio.entity.Usuario;
import com.br.desafio.exceptions.ServiceException;
import com.br.desafio.exceptions.VotoException;
import com.br.desafio.repository.UsuarioRepository;
import com.br.desafio.services.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        repository.save(usuario);
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorCpf(String cpfUsuario) {
        return repository.findByUsuarioCpf(cpfUsuario);
    }

    private Usuario validarUsuario(Usuario usuario){
        Usuario usuarioVerificado = repository.findByUsuarioCpf(usuario.getCpf());
        if(usuarioVerificado != null){
            throw new ServiceException("Usuário já cadastrado.");
        }
        return usuario;
    }



}
