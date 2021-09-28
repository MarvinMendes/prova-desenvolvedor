package com.marvin.provadesenvolvedor.repository;

import com.marvin.provadesenvolvedor.domain.Registro;
import com.marvin.provadesenvolvedor.domain.enums.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {

    Optional<List<Registro>> findRegistroByDescricaoOrAnoPublicacaoOrAutorOrTipoRegistroOrDataInclusao
            (String descricao, Year anoPublicacao, String autor, Tipo tipoRegistro, LocalDateTime dataInclusao);

    @Override
    Optional<Registro> findById(Long id);
}
