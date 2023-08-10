package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.dto.DespesaCreateDTO;
import br.com.dbc.vemser.walletlife.dto.DespesaDTO;
import br.com.dbc.vemser.walletlife.dto.UsuarioDTO;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Despesa;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.repository.DespesaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class DespesaService {

    private final DespesaRepository despesaRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;

    // criação de um objeto
    public DespesaDTO adicionarDespesa(DespesaCreateDTO despesa) throws RegraDeNegocioException{
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(despesa.getIdFK());
            Usuario usuarioConvertido = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioConvertido != null) {
                Despesa entity = objectMapper.convertValue(despesa, Despesa.class);
                entity.setValor(despesa.getValor());
                entity.setDescricao(despesa.getDescricao());
                entity.setTipo(despesa.getTipo());
                entity.setIdFK(despesa.getIdFK());
                Despesa despesaAdicionada = despesaRepository.adicionar(entity);

                if (despesaAdicionada != null){
                    DespesaDTO despesaDTO = convertToDTO(despesaAdicionada);
                    return despesaDTO;
                }else{
                    throw new RegraDeNegocioException("Investimento não adicionado");
                }
            } else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // remoção
    public void removerDespesa(Integer idDespesa) throws RegraDeNegocioException {
        try {
            if (buscarById(idDespesa) != null) {
                despesaRepository.remover(idDespesa);
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public DespesaDTO editarDespesa(Integer id, DespesaCreateDTO despesa) throws RegraDeNegocioException{
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(despesa.getIdFK());
            Usuario usuarioConvertido = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioConvertido != null){
                Despesa entity = objectMapper.convertValue(despesa, Despesa.class);
                entity.setValor(despesa.getValor());
                entity.setDescricao(despesa.getDescricao());
                entity.setTipo(despesa.getTipo());
                entity.setIdFK(despesa.getIdFK());
                Despesa despesaAtualizada = despesaRepository.editar(id, entity);

                if (despesaAtualizada != null){
                    DespesaDTO despesaDTO = convertToDTO(despesaAtualizada);
                    return despesaDTO;
                }else{
                    throw new RegraDeNegocioException("Despesa não encontrado");
                }
            }else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura
    public List<DespesaDTO> listarDespesaByIdUsuario(Integer idUsuario) throws RegraDeNegocioException{
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(idUsuario);

            if (usuarioById != null) {
                List<Despesa> despesas = despesaRepository.listarPorIdUsuario(idUsuario);
                List<DespesaDTO> despesasDTO = this.convertToDTOList(despesas);
                return despesasDTO;
            } else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<DespesaDTO> listarTodos() {
        try {
            List<Despesa> despesas = despesaRepository.listar();
            List<DespesaDTO> despesasDTO = this.convertToDTOList(despesas);
            return despesasDTO;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DespesaDTO buscarById(Integer idDespesa) throws RegraDeNegocioException {
        try {
            Despesa despesa = despesaRepository.buscarPorId(idDespesa);
            if (despesa.getId() == null){
                throw new RegraDeNegocioException("Despesa não encontrada");
            }
            DespesaDTO despesaDTO = convertToDTO(despesa);

            return despesaDTO;

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DespesaDTO convertToDTO(Despesa despesa){
        DespesaDTO despesaDTO = objectMapper.convertValue(despesa, DespesaDTO.class);

        return despesaDTO;
    }

    private List<DespesaDTO> convertToDTOList(List<Despesa> listaDespesas){
        return listaDespesas.stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }
}
