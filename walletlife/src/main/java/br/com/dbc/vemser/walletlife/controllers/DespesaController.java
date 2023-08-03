package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.modelos.Despesa;
import br.com.dbc.vemser.walletlife.service.DespesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController {
    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService){
        this.despesaService = despesaService;
    }

    @GetMapping("/{id}") //GET localhost:8080/despesa/1
    public List<Despesa> listarDespesas(@PathVariable Integer id){
        return despesaService.listarDespesa(id);

    }
    @PostMapping //POST localhost:8080/despesa
    public void adicionarDespesa(@RequestBody Despesa despesa){
        despesaService.adicionarDespesa(despesa);
    }
    @PutMapping("/{idDespesa}") //PUT localhost:8080/despesa/1
    public Despesa editarDepesa(@PathVariable("idDepesa") Integer id,
                                 @RequestBody Despesa despesaAtualizar) throws Exception {
        return despesaService.editarDespesa(id, despesaAtualizar);
    }

    @DeleteMapping("/{id}") //DELETE localhost:8080/despesa/1
    public void removerDespesa(@PathVariable Integer id){
        despesaService.removerDespesa(id);
    }

}
