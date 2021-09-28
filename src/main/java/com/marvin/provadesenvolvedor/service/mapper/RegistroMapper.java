package com.marvin.provadesenvolvedor.service.mapper;

import com.marvin.provadesenvolvedor.domain.Registro;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoRegistroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class RegistroMapper {

    public static final RegistroMapper INSTANCE = Mappers.getMapper(RegistroMapper.class);

    public abstract Registro toEntity(RequisicaoRegistroDTO dto);

    public abstract RequisicaoRegistroDTO toDTO(Registro registro);


}
