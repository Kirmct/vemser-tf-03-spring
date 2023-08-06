package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.dto.UsuarioCreateDTO;
import br.com.dbc.vemser.walletlife.dto.UsuarioDTO;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Data
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;



    // criação de um objeto
    public UsuarioDTO adicionarUsuario(UsuarioCreateDTO usuario) {
        UsuarioDTO novoUsuario = new UsuarioDTO();
        try {
            Usuario usuarioConvertido = objectMapper.convertValue(usuario, Usuario.class);
            Usuario usuarioCriado = usuarioRepository.adicionar(usuarioConvertido);
            novoUsuario = this.convertToDTO(usuarioCriado);

            System.out.println("Usuario Criado " + usuarioCriado);

           return novoUsuario;

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return novoUsuario;
    }

    public boolean removerPessoa(Integer id) {
        try {
            Usuario usuario = usuarioRepository.buscarPorId(id);
            if (usuario.getId() == null){
                throw new RegraDeNegocioException("Usuário não encontrado");
            }
            boolean conseguiuRemover = usuarioRepository.remover(id);
            return conseguiuRemover;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // atualização de um objeto
    public UsuarioDTO editarPessoa(Integer id, UsuarioCreateDTO usuario) {
        try {
            Usuario usuarioExiste = usuarioRepository.buscarPorId(id);
            if (usuarioExiste.getId() == null){
                throw new RegraDeNegocioException("Usuário não encontrado");
            }
            Usuario usuarioConvertido = objectMapper.convertValue(usuario, Usuario.class);
            Usuario conseguiuEditar = usuarioRepository.editar(id, usuarioConvertido);
            UsuarioDTO usuarioDTO = this.convertToDTO(conseguiuEditar);
            return usuarioDTO;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // leitura
    public UsuarioDTO listarPessoasPorId(Integer id) {
        try {
            Usuario listar = usuarioRepository.buscarPorId(id);
            UsuarioDTO usuarioDTO = this.convertToDTO(listar);

            if(listar.getId() == null){
                throw new RegraDeNegocioException("Usuário não encontrado");
            }

            return usuarioDTO;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<UsuarioDTO> listar() throws BancoDeDadosException {
        List<Usuario> usuarios = usuarioRepository.listar();
        List<UsuarioDTO> usuarioDTOS = this.convertToDTOList(usuarios);
        return usuarioDTOS;
    }

    private UsuarioDTO convertToDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNomeCompleto(usuario.getNomeCompleto());
        usuarioDTO.setDataNascimento(usuario.getDataNascimento());

        return usuarioDTO;
    }

    private List<UsuarioDTO> convertToDTOList(List<Usuario> listaUsuarios){
        return listaUsuarios.stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }

}
