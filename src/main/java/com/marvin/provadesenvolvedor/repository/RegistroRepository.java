package com.marvin.provadesenvolvedor.repository;

import com.marvin.provadesenvolvedor.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
}
