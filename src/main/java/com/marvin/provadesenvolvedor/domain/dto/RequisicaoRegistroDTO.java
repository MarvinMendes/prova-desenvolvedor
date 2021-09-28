package com.marvin.provadesenvolvedor.domain.dto;

import com.marvin.provadesenvolvedor.domain.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RequisicaoRegistroDTO {


    private String descricao;

    private LocalDate anoPublicacao;

    private String autor;

    private Tipo tipoRegistro;

}
