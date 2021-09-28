package com.marvin.provadesenvolvedor.repository;

import com.marvin.provadesenvolvedor.domain.Registro;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoBuscaRegistroDTO;
import com.marvin.provadesenvolvedor.domain.enums.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    Optional<Registro> findRegistroByDescricaoOrAnoPublicacaoOrAutorOrTipoRegistroOrDataInclusao
            (String descricao, LocalDate anoPublicacao, String autor, Tipo tipoRegistro, LocalDateTime dataInclusao);

}
