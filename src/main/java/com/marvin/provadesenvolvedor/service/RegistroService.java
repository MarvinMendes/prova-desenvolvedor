package com.marvin.provadesenvolvedor.service;

import com.marvin.provadesenvolvedor.domain.Registro;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoBuscaRegistroDTO;
import com.marvin.provadesenvolvedor.domain.dto.RequisicaoRegistroDTO;
import com.marvin.provadesenvolvedor.domain.exceptions.ParametroNaoValidoException;
import com.marvin.provadesenvolvedor.domain.exceptions.RegistroFoiExcluidoDaBaseDeDadosException;
import com.marvin.provadesenvolvedor.domain.exceptions.RegistroNaoEncontradoException;
import com.marvin.provadesenvolvedor.repository.RegistroRepository;
import com.marvin.provadesenvolvedor.service.mapper.RegistroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroService {

    private static final String REGISTRO_NAO_ENCONTRADO ="O registro buscado não foi encontrado na base de dados.";
    private static final String REGISTRO_EXCLUIDO = "O registro buscado foi excluído da base de dados, favor entrar em contato com o suporte.";
    private static final String PARAMETROS_INVALIDOS = "Todos os parâmetros devem ser passados corretamente. São eles: Descrição, Tipo do registro, Autor/Escritor e Ano de publicação." ;

    private RegistroRepository registroRepository;
    private final RegistroMapper mapper = RegistroMapper.INSTANCE;

    @Autowired
    public RegistroService(RegistroRepository rp) {
        this.registroRepository = rp;
    }

    @Transactional
    public RequisicaoRegistroDTO salvar(RequisicaoRegistroDTO dto) {
        Registro registroSalvo = registroRepository.save(mapper.toEntity(dto));

        return mapper.toDTO(registroSalvo);
    }

    @Transactional(readOnly = true)
    public List<RequisicaoBuscaRegistroDTO> buscar(RequisicaoBuscaRegistroDTO dto) throws RegistroNaoEncontradoException, RegistroFoiExcluidoDaBaseDeDadosException {


        Optional<List<Registro>> registro = registroRepository.
                findRegistroByDescricaoOrAnoPublicacaoOrAutorOrTipoRegistroOrDataInclusao(dto.getDescricao(),
                        dto.getAnoPublicacao(), dto.getAutor(), dto.getTipoRegistro(), dto.getDataInclusao());

       /* if (registro.isPresent()) {
            List<Registro> registroDaBaseDeDados = registro.get();
            if (!registroDaBaseDeDados.) {
                throw new RegistroFoiExcluidoDaBaseDeDadosException(REGISTRO_EXCLUIDO);
            }
            return mapper.toDTOBusca(registroDaBaseDeDados);
        }*/

        if (registro.isPresent()) {
            List<Registro> registros = registro.get();

            return registros.stream().filter(Registro::getAtivo)
                    .map(mapper::toDTOBusca)
                    .collect(Collectors.toList());

        }

        throw new RegistroNaoEncontradoException(REGISTRO_NAO_ENCONTRADO);
    }

    @Transactional
    public RequisicaoRegistroDTO atualizaRegistro(Long id, RequisicaoRegistroDTO dto) throws
            RegistroFoiExcluidoDaBaseDeDadosException, RegistroNaoEncontradoException, ParametroNaoValidoException {
        Optional<Registro> registroDaBaseDeDados = Optional.ofNullable
                (registroRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(REGISTRO_NAO_ENCONTRADO)));
        if (registroDaBaseDeDados.isPresent() && !registroDaBaseDeDados.get().getAtivo()) {
            throw new RegistroFoiExcluidoDaBaseDeDadosException(REGISTRO_EXCLUIDO);
        }

        Registro registro = registroDaBaseDeDados.get();

        if (VerificaDadosDaRequisicao(dto)) {

            registro.setDataAlteracao(LocalDateTime.now());
            registro.setDescricao(dto.getDescricao());
            registro.setAutor(dto.getAutor());
            registro.setTipoRegistro(dto.getTipoRegistro());
            registro.setAnoPublicacao(dto.getAnoPublicacao());


            registroRepository.save(registro);
        }

        return mapper.toDTO(registro);
    }


    private boolean VerificaDadosDaRequisicao(RequisicaoRegistroDTO dto) throws ParametroNaoValidoException {
        if (dto.getDescricao().isEmpty() || dto.getDescricao() == null) {
            throw new ParametroNaoValidoException(PARAMETROS_INVALIDOS);
        }
        if (dto.getTipoRegistro() == null) {
            throw new ParametroNaoValidoException(PARAMETROS_INVALIDOS);
        }
        if (dto.getAutor().isEmpty() || dto.getAutor() == null) {
            throw new ParametroNaoValidoException(PARAMETROS_INVALIDOS);
        }
        if (dto.getAnoPublicacao() == null) {
            throw new ParametroNaoValidoException(PARAMETROS_INVALIDOS);
        }

        return true;
    }

    @Transactional
    public void deletar(Long id) throws RegistroNaoEncontradoException {
        Registro registro = registroRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(REGISTRO_NAO_ENCONTRADO));
        registro.setAtivo(false);
        registro.setDataAlteracao(LocalDateTime.now());
        registro.setDataExclusao(LocalDateTime.now());
        registroRepository.save(registro);
    }
}
