package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
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
    public void adicionarUsuario(Usuario usuario) {
        try {
            if (usuario.getCpf().length() != 11) {
                throw new Exception("CPF Invalido!");
            }

            Usuario pessoaAdicionada = usuarioRepository.adicionar(usuario);

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public void removerPessoa(Integer id) {
        try {
            boolean conseguiuRemover = usuarioRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public Usuario editarPessoa(Integer id, Usuario usuario) {
        try {
            Usuario conseguiuEditar = usuarioRepository.editar(id, usuario);
            return conseguiuEditar;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura
    public Usuario listarPessoasPorId(Integer id) {
        try {
            Usuario listar = usuarioRepository.listarPorId(id);
            return listar;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listar() throws BancoDeDadosException {
        return usuarioRepository.listar();
    }

}
