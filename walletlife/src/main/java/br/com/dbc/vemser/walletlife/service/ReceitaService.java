package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.dto.ReceitaCreateDTO;
import br.com.dbc.vemser.walletlife.dto.ReceitaDTO;
import br.com.dbc.vemser.walletlife.dto.UsuarioDTO;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Receita;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.repository.ReceitaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;


    // criação
    public ReceitaDTO adicionarReceita(ReceitaCreateDTO receita) throws RegraDeNegocioException {
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(receita.getIdFK());
            Usuario usuarioConvertido = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioConvertido != null) {
                Receita entity = objectMapper.convertValue(receita, Receita.class);

                Receita receitaAdicionada = receitaRepository.adicionar(entity);
                ReceitaDTO receitaDTO = convertToDTO(receitaAdicionada);

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
            ReceitaDTO entityDTO = buscarById(id);
            Receita entity = objectMapper.convertValue(entityDTO, Receita.class);

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
    public List<ReceitaDTO> listarTodos() {
        try {
            List<Receita> receitas = receitaRepository.listar();
            List<ReceitaDTO> receitasDTO = this.convertToDTOList(receitas);
            return receitasDTO;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leitura por usuario
    public List<ReceitaDTO> buscarByIdUsuario(Integer idUsuario) throws RegraDeNegocioException {
        try {
            UsuarioDTO usuarioById = usuarioService.listarPessoasPorId(idUsuario);
            Usuario usuarioConvertido = objectMapper.convertValue(usuarioById, Usuario.class);

            if (usuarioConvertido != null) {
                List<Receita> receitas = receitaRepository.listarPorIdUsuario(idUsuario);
                List<ReceitaDTO> receitasDTO = this.convertToDTOList(receitas);
                return receitasDTO;
            } else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura por id da receita
    public ReceitaDTO buscarById(Integer idReceita) throws RegraDeNegocioException {
        try {
            Receita receita = receitaRepository.buscarPorId(idReceita);
            if (receita != null) {
                ReceitaDTO receitaDTO = convertToDTO(receita);
                return receitaDTO;
            } else {
                throw new RegraDeNegocioException("Receita não encontrada");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ReceitaDTO convertToDTO(Receita receita){
        ReceitaDTO receitaDTO = objectMapper.convertValue(receita, ReceitaDTO.class);

        return receitaDTO;
    }

    private List<ReceitaDTO> convertToDTOList(List<Receita> listaReceitas){
        return listaReceitas.stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

}
