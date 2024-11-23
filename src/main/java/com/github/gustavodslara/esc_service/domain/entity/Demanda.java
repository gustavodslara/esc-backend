package com.github.gustavodslara.esc_service.domain.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Demanda {
    Capacitação("Capacitação"),
    OUTRA_DEMANDA("Outra Demanda"),
    Externa("Externa"),
    Interna("Interna");

    private final String descricao;

    Demanda(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
