package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // criação de um objeto
    public Usuario adicionarUsuario(Usuario usuario) {
        Usuario novoUsuario = new Usuario();
        try {
//            if (usuario.getCpf().length() != 11) {
//                throw new Exception("CPF Invalido!");
//            }

           novoUsuario = usuarioRepository.adicionar(usuario);

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
            Usuario usuario = usuarioRepository.listarPorId(id);
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
    public Usuario editarPessoa(Integer id, Usuario usuario) {
        try {
            Usuario usuarioExiste = usuarioRepository.listarPorId(id);
            if (usuarioExiste.getId() == null){
                throw new RegraDeNegocioException("Usuário não encontrado");
            }
            Usuario conseguiuEditar = usuarioRepository.editar(id, usuario);
            return conseguiuEditar;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // leitura
    public Usuario listarPessoasPorId(Integer id) {
        try {
            Usuario listar = usuarioRepository.listarPorId(id);
            if(listar.getId() == null){
                throw new RegraDeNegocioException("Usuário não encontrado");
            }
            return listar;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Usuario> listar() throws BancoDeDadosException {
        return usuarioRepository.listar();
    }

}
