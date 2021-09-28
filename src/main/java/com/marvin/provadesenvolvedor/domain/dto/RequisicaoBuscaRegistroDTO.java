package com.marvin.provadesenvolvedor.domain.dto;

import com.marvin.provadesenvolvedor.domain.enums.Tipo;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RequisicaoBuscaRegistroDTO {


    private String descricao;

    private LocalDate anoPublicacao;

    private String autor;

    private Tipo tipoRegistro;

    private LocalDateTime dataInclusao;

    private boolean ativo;

}
