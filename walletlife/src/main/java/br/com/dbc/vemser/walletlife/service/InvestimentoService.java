package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.dto.InvestimentoCreateDTO;
import br.com.dbc.vemser.walletlife.dto.InvestimentoDTO;
import br.com.dbc.vemser.walletlife.dto.UsuarioDTO;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.repository.InvestimentoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvestimentoService {
    private InvestimentoRepository investimentoRepository;
    private UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    // criação de um objeto
    public InvestimentoDTO adicionarInvestimento(InvestimentoCreateDTO investimento) throws RegraDeNegocioException {
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(investimento.getIdFK());
            Usuario usuarioConvertido = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioConvertido != null){
                Investimento entity = objectMapper.convertValue(investimento, Investimento.class);

                entity.setValor(investimento.getValor());
                entity.setDescricao(investimento.getDescricao());
                entity.setDataInicio(investimento.getDataInicio());
                entity.setTipo(investimento.getTipo());
                entity.setCorretora(investimento.getCorretora());
                entity.setIdFK(investimento.getIdFK());

                Investimento investimentoAdicionado = investimentoRepository.adicionar(entity);

                if (investimentoAdicionado != null){
                    InvestimentoDTO investimentoDTO = convertToDTO(investimentoAdicionado);
                    return investimentoDTO;
                }else{
                    throw new RegraDeNegocioException("Investimento não adicionado");
                }
            }else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // remoção
    public void removerInvestimento(Integer id) {
        try {
            investimentoRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public InvestimentoDTO editarInvestimento(Integer id, InvestimentoCreateDTO investimento) throws RegraDeNegocioException  {
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(investimento.getIdFK());
            Usuario usuarioConvertid = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioConvertid != null){
                Investimento entity = objectMapper.convertValue(investimento, Investimento.class);

                entity.setValor(investimento.getValor());
                entity.setDescricao(investimento.getDescricao());
                entity.setDataInicio(investimento.getDataInicio());
                entity.setTipo(investimento.getTipo());
                entity.setCorretora(investimento.getCorretora());
                entity.setIdFK(investimento.getIdFK());

                Investimento investimentoAtualizado = investimentoRepository.editar(id, entity);

                if (investimentoAtualizado != null){
                    InvestimentoDTO investimentoDTO = convertToDTO(investimentoAtualizado);
                    return investimentoDTO;
                }else{
                    throw new RegraDeNegocioException("Investimento não encontrado");
                }
            }else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura por id do investimento
    public InvestimentoDTO buscarById(Integer idInvestimento) {
        try {
            Investimento investimento = investimentoRepository.buscarPorId(idInvestimento);

            InvestimentoDTO investimentoDTO = convertToDTO(investimento);
            return investimentoDTO;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura geral
    public List<Investimento> listarTodos() {
        try {
            return investimentoRepository.listar();
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leitura por usuario
    public List<InvestimentoDTO> buscarByIdUsuario(Integer idUsuario) throws RegraDeNegocioException   {
        try {

            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(idUsuario);
            Usuario usuarioConvertido = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioById != null){
                List<Investimento> investimento = investimentoRepository.listarPorIdUsuario(idUsuario);
                List<InvestimentoDTO> investimentoDTO = convertToDTOList(investimento);

                return investimentoDTO;
            }else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Investimento convertToEntity(InvestimentoCreateDTO investimentoDTO) {
        Investimento investimento = new Investimento();
        investimento.setValor(investimentoDTO.getValor());
        investimento.setDescricao(investimentoDTO.getDescricao());
        investimento.setDataInicio(investimentoDTO.getDataInicio());
        investimento.setTipo(investimentoDTO.getTipo());
        investimento.setCorretora(investimentoDTO.getCorretora());
        investimento.setIdFK(investimentoDTO.getIdFK());
        return investimento;
    }
    public InvestimentoDTO convertToDTO(Investimento investimento) {
        InvestimentoDTO investimentoDTO = new InvestimentoDTO();
        investimentoDTO.setId_investimento(investimento.getId());
        investimentoDTO.setValor(investimento.getValor());
        investimentoDTO.setDescricao(investimento.getDescricao());
        investimentoDTO.setDataInicio(investimento.getDataInicio());
        investimentoDTO.setTipo(investimento.getTipo());
        investimentoDTO.setCorretora(investimento.getCorretora());
        investimentoDTO.setIdFK(investimento.getIdFK());
        return investimentoDTO;
    }

    public List<InvestimentoDTO> convertToDTOList(List<Investimento> investimentos) {
        return investimentos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
