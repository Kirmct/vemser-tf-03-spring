package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.dto.ReceitaCreateDTO;
import br.com.dbc.vemser.walletlife.dto.ReceitaDTO;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.modelos.Receita;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.repository.ReceitaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;

    // criação
    public ReceitaDTO adicionarReceita(ReceitaCreateDTO receita) throws RegraDeNegocioException {
        try {
            Usuario usuarioById = usuarioService.listarPessoasPorId(receita.getIdFK());
            System.out.println(usuarioById);

            if (usuarioById != null) {
                Receita entity = objectMapper.convertValue(receita, Receita.class);

                Receita receitaAdicionada = receitaRepository.adicionar(entity);
                ReceitaDTO receitaDTO = new ReceitaDTO();
                receitaDTO.setId(receitaAdicionada.getId());
                receitaDTO.setDescricao(receitaAdicionada.getDescricao());
                receitaDTO.setTipo(receitaAdicionada.getTipo());
                receitaDTO.setValor(receitaAdicionada.getValor());
                receitaDTO.setIdFK(receitaAdicionada.getIdFK());
                receitaDTO.setBanco(receitaAdicionada.getBanco());
                receitaDTO.setEmpresa(receitaAdicionada.getEmpresa());

                return receitaDTO;
            } else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // remoção
    public void removerReceita(Integer idReceita) throws RegraDeNegocioException {
        try {
            if (buscarById(idReceita) != null) {
                receitaRepository.remover(idReceita);
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização
    public ReceitaDTO editarReceita(Integer id, ReceitaDTO receita) throws RegraDeNegocioException {
        try {
            receita.setId(id);
            Receita entity = buscarById(id);

            if (entity != null) {
                entity.setDescricao(receita.getDescricao());
                entity.setTipo(receita.getTipo());
                entity.setValor(receita.getValor());
                entity.setIdFK(receita.getIdFK());
                entity.setBanco(receita.getBanco());
                entity.setEmpresa(receita.getEmpresa());
                receitaRepository.editar(id, entity);
                return receita;
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura geral
    public List<Receita> listarTodos() {
        try {
            return receitaRepository.listar();
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leitura por usuario
    public List<Receita> buscarByIdUsuario(Integer idUsuario) throws RegraDeNegocioException {
        try {
            Usuario usuarioById = usuarioService.listarPessoasPorId(idUsuario);

            if (usuarioById != null) {
                return receitaRepository.listarPorIdUsuario(idUsuario);
            } else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura por id da receita
    public Receita buscarById(Integer idReceita) throws RegraDeNegocioException {
        try {
            Receita receita = receitaRepository.buscarPorId(idReceita);
            if (receita != null) {
                return receita;
            } else {
                throw new RegraDeNegocioException("Receita não encontrada");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
