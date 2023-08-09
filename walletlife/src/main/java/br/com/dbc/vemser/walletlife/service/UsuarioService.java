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

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Data
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;


    // criação de um objeto
    public UsuarioDTO adicionarUsuario(UsuarioCreateDTO usuario) {
        UsuarioDTO novoUsuario = new UsuarioDTO();
        try {
            Usuario usuarioConvertido = objectMapper.convertValue(usuario, Usuario.class);
            Usuario usuarioCriado = usuarioRepository.adicionar(usuarioConvertido);
            novoUsuario = this.convertToDTO(usuarioCriado);

            Map<String, String> dados = new HashMap<>();
            dados.put("nome", novoUsuario.getNomeCompleto());
            String paragrafo = "Estamos felizes em tê-lo como usuário do Wallet Life! :) <br>" +
                    "           Seu cadastro foi realizado com sucesso, e agora você pode organizar todas suas finanças!.<br>" +
                    "           Aproveite para acessar nossa plataforma e descobrir mais sobre o projeto!<br>";
            dados.put("paragrafo", paragrafo);
            dados.put("email", novoUsuario.getEmail());
            emailService.sendTemplateEmail(dados);

           return novoUsuario;

        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
        return novoUsuario;
    }

    public void removerPessoa(Integer id) {
        try {
            Usuario usuario = usuarioRepository.buscarPorId(id);
            if (usuario.getId() == null){
                throw new RegraDeNegocioException("Usuário não encontrado");
            }
            usuarioRepository.remover(id);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        }
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


            Map<String, String> dados = new HashMap<>();
            dados.put("nome", usuarioDTO.getNomeCompleto());
            String paragrafo = "Parece que você atualizou seus dados!<br>" +
                               "Deu tudo certo na operação.<br>" +
                               "Pode ficar tranquile! :)";
            dados.put("paragrafo", paragrafo);
            dados.put("email", usuarioDTO.getEmail());
            emailService.sendTemplateEmail(dados);

            return usuarioDTO;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (RegraDeNegocioException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
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
