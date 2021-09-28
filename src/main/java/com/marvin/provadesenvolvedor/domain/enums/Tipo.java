package com.marvin.provadesenvolvedor.domain.enums;

import lombok.Getter;

@Getter
public enum Tipo {

    LIVRO("Livro"),
    DOCUMENTOS_RAROS("Documentos raros"),
    MANUSCRITO("Manuscrito");

    private final String descricao;

    Tipo(String descricao) {
        this.descricao = descricao;
    }

}
