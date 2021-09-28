package com.marvin.provadesenvolvedor.service;

import com.marvin.provadesenvolvedor.domain.Registro;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoBuscaRegistroDTO;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoRegistroDTO;
import com.marvin.provadesenvolvedor.domain.exceptions.RegistroFoiExcluidoDaBaseDeDadosException;
import com.marvin.provadesenvolvedor.domain.exceptions.RegistroNaoEncontradoException;
import com.marvin.provadesenvolvedor.repository.RegistroRepository;
import com.marvin.provadesenvolvedor.service.mapper.RegistroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistroService {

    private static final String REGISTRO_NAO_ENCONTRADO ="O registro buscado não foi encontrado na base de dados.";
    private static final String REGISTRO_EXCLUIDO = "O registro buscado foi excluído da base de dados, favor entrar em contato com o suporte.";

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

    @Transactional(readOnly = true)
    public RequisicaoBuscaRegistroDTO buscar(RequisicaoBuscaRegistroDTO dto) throws RegistroNaoEncontradoException, RegistroFoiExcluidoDaBaseDeDadosException {


        Optional<Registro> registro = registroRepository.
                findRegistroByDescricaoOrAnoPublicacaoOrAutorOrTipoRegistroOrDataInclusao(dto.getDescricao(),
                        dto.getAnoPublicacao(), dto.getAutor(), dto.getTipoRegistro(), dto.getDataInclusao());

        if (registro.isPresent()) {
            Registro registroDaBaseDeDados = registro.get();
            if (!registroDaBaseDeDados.getAtivo()) {
                throw new RegistroFoiExcluidoDaBaseDeDadosException(REGISTRO_EXCLUIDO);
            }
            return mapper.toDTOBusca(registroDaBaseDeDados);
        }
        throw new RegistroNaoEncontradoException(REGISTRO_NAO_ENCONTRADO);
    }



}
