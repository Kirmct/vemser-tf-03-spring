package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping
    public List<Usuario> listar() throws BancoDeDadosException {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario listarPessoasPorId(@PathVariable Integer id){
        return usuarioService.listarPessoasPorId(id);
    }

    @PostMapping
    public void adicionarUsuario(@RequestBody Usuario usuario){
        usuarioService.adicionarUsuario(usuario);
    }

    @PutMapping("/{idUsuario}")
    public Usuario editarPessoa(@PathVariable Integer idUsuario, @RequestBody Usuario usuario){
        return usuarioService.editarPessoa(idUsuario, usuario);
    }

    @DeleteMapping("/{idUsuario}")
    public void remover(@PathVariable Integer idUsuario){
        usuarioService.removerPessoa(idUsuario);
    }

}
