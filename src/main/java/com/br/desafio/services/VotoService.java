package com.br.desafio.services;

import com.br.desafio.domain.CpfIntegracaoDTO;
import com.br.desafio.entity.Voto;
import org.springframework.stereotype.Service;

@Service
public interface VotoService {

    Voto votar (Voto voto);

    CpfIntegracaoDTO votarIntegracao (Voto voto);

}
