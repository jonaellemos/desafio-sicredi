package com.br.desafio.services.impl;

import com.br.desafio.services.MensageiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MensageiroServiceImpl implements MensageiroService {

    public static final String TOPIC = "sumario-pautas";

    public KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MensageiroServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarMensagem(String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
}
