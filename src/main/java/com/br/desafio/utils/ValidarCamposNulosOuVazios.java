package com.br.desafio.utils;

import java.util.Collection;

public interface ValidarCamposNulosOuVazios {

    static boolean camporVazioOuNulo(Object valor) {
        return validarCampoNulo(valor) || validarCampoVazio(valor);
    }

    static boolean validarCampoNulo(Object valor) {
        return valor == null;
    }

    static boolean validarCampoVazio(Object valor) {
        return valor instanceof Collection ? ((Collection) valor).isEmpty() : "".equals(valor.toString().trim());
    }
}
