package com.marvin.provadesenvolvedor.service;

import com.marvin.provadesenvolvedor.domain.Registro;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoRegistroDTO;
import com.marvin.provadesenvolvedor.repository.RegistroRepository;
import com.marvin.provadesenvolvedor.service.mapper.RegistroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroService {


    private RegistroRepository registroRepository;
    private final RegistroMapper mapper = RegistroMapper.INSTANCE;

    @Autowired
    public RegistroService(RegistroRepository rp) {
        this.registroRepository = rp;
    }

    @Transactional
    public RequisicaoRegistroDTO save(RequisicaoRegistroDTO dto) {
        Registro registroSalvo = registroRepository.save(mapper.toEntity(dto));

        return mapper.toDTO(registroSalvo);
    }
}
