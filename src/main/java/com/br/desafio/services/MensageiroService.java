package com.br.desafio.services;

import org.springframework.stereotype.Service;

@Service
public interface MensageiroService {
    void enviarMensagem(String message);
}
