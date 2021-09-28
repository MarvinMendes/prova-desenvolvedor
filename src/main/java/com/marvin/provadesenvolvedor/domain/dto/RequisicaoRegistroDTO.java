package com.marvin.provadesenvolvedor.domain.dto;

import com.marvin.provadesenvolvedor.domain.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.Year;

@Data
@AllArgsConstructor
public class RequisicaoRegistroDTO {

    private Long id;

    @NonNull
    private String descricao;

    @NonNull
    private Year anoPublicacao;

    @NonNull
    private String autor;

    @NonNull
    private Tipo tipoRegistro;


}
