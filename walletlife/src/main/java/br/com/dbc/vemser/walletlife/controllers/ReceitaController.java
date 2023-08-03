package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.modelos.Receita;
import br.com.dbc.vemser.walletlife.service.ReceitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receita")
public class ReceitaController {
    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping("/{id}")
    public List<Receita> listarReceitasPorUsuario(@PathVariable Integer id){
        return receitaService.listarById(id);
    }

    @PostMapping
    public void adicionarReceita(@RequestBody Receita receita){
        receitaService.adicionarReceita(receita);
    }

    @PutMapping("/{idReceita}")
    public Receita editarReceita(@PathVariable("idReceita") Integer id,
                           @RequestBody Receita receitaAtualizar) throws Exception {
        return receitaService.editarReceita(id, receitaAtualizar);
    }

    @DeleteMapping("/{id}")
    public void removerReceita(@PathVariable Integer id){
        receitaService.removerReceita(id);
    }

}
