package br.com.dbc.vemser.walletlife.controllers;

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

    @GetMapping("/{id}")
    public List<Usuario> listarPessoasPorId(@PathVariable Integer id){
        return usuarioService.listarPessoasPorId(id);
    }

    @PostMapping
    public void adicionarUsuario(@RequestBody Usuario usuario){
        usuarioService.adicionarUsuario(usuario);
    }

}
