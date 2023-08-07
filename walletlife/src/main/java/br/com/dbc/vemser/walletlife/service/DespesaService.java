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
                Despesa despesaAdicionada = despesaRepository.adicionar(entity);

                DespesaDTO despesaDTO = new DespesaDTO();

                despesaDTO.setTipo(despesaAdicionada.getTipo());
                despesaDTO.setValor(despesaAdicionada.getValor());
                despesaDTO.setDescricao(despesaAdicionada.getDescricao());
                despesaDTO.setIdFK(despesaAdicionada.getIdFK());

                return despesaDTO;
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
    public DespesaDTO editarDespesa(Integer id, DespesaDTO despesa) throws RegraDeNegocioException{
        try {
            despesa.setId(id);
            Despesa entity = buscarById(id);
            if (entity != null) {
                entity.setDescricao(despesa.getDescricao());
                entity.setTipo(despesa.getTipo());
                entity.setValor(despesa.getValor());
                entity.setIdFK(despesa.getIdFK());
                despesaRepository.editar(id, entity);
                return despesa;
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura
    public List<Despesa> listarDespesaByIdUsuario(Integer idUsuario) throws RegraDeNegocioException{
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(idUsuario);
            Usuario usuarioConvertido = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioConvertido != null) {
                return despesaRepository.listarPorIdUsuario(idUsuario);
            } else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Despesa> listarTodos() {
        try {
            return despesaRepository.listar();
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Despesa buscarById(Integer idDespesa) throws RegraDeNegocioException {
        try {
            Despesa despesa = despesaRepository.buscarPorId(idDespesa);
            if (despesa != null) {
                return despesa;
            } else {
                throw new RegraDeNegocioException("Despesa não encontrada");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }


}
