package com.marvin.provadesenvolvedor.domain;

import com.marvin.provadesenvolvedor.domain.enums.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Year anoPublicacao;

    @Column(nullable = false)
    private String autor;

    private LocalDateTime dataInclusao = LocalDateTime.now();

    private LocalDateTime dataAlteracao = null;

    private LocalDateTime dataExclusao = null;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Tipo tipoRegistro;

    private boolean ativo = true;

    public boolean getAtivo() {
        return this.ativo;
    }

}
