package com.br.desafio.controllers;

import com.br.desafio.domain.UsuarioDTO;
import com.br.desafio.entity.Usuario;
import com.br.desafio.mapper.UsuarioMapper;
import com.br.desafio.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.br.desafio.mapper.UsuarioMapper.fromEntity;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        Usuario novoUsuario = usuarioService.criarUsuario(UsuarioMapper.fromDTO(usuarioDTO));
        return new ResponseEntity<>(fromEntity(novoUsuario), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> buscarUsuarioPorCpf(@RequestParam @Valid String cpfUsuario){
        Usuario usuarioConsulta = usuarioService.buscarUsuarioPorCpf(cpfUsuario);

        if(usuarioConsulta == null){
            return new ResponseEntity<>("Messagem: Usuário não encontrado.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(fromEntity(usuarioConsulta), HttpStatus.OK);
    }

}
