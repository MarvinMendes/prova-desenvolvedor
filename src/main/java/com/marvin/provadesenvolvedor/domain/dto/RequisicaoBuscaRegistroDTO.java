package com.marvin.provadesenvolvedor.domain.dto;

import com.marvin.provadesenvolvedor.domain.enums.Tipo;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.Year;

@Data
public class RequisicaoBuscaRegistroDTO {

    private Long id;

    private String descricao;

    private Year anoPublicacao;

    private String autor;

    private Tipo tipoRegistro;

    private LocalDateTime dataInclusao;

}
