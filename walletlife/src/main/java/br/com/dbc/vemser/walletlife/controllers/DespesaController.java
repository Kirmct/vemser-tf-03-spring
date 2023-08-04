package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.modelos.Despesa;
import br.com.dbc.vemser.walletlife.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/despesa")
public class DespesaController {
    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService){
        this.despesaService = despesaService;
    }

    @GetMapping("/{id}") //GET localhost:8080/despesa/1
    public Despesa listarDespesas(@PathVariable Integer id){
        return despesaService.listarDespesa(id);

    }
    @PostMapping //POST localhost:8080/despesa
    public ResponseEntity<Void> adicionarDespesa(@Valid @RequestBody Despesa despesa){
        despesaService.adicionarDespesa(despesa);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idDespesa}") //PUT localhost:8080/despesa/1
    public ResponseEntity<Despesa> editarDepesa(@Valid @PathVariable("idDepesa") Integer id,
                                                @Valid @RequestBody Despesa despesaAtualizar) throws Exception {
        Despesa despesaAtualizada = despesaService.editarDespesa(id, despesaAtualizar);
        return ResponseEntity.ok(despesaAtualizada);
    }

    @DeleteMapping("/{id}") //DELETE localhost:8080/despesa/1
    public ResponseEntity<Void> removerDespesa(@Valid @PathVariable Integer id){
        despesaService.removerDespesa(id);
        return ResponseEntity.ok().build();
    }
}
