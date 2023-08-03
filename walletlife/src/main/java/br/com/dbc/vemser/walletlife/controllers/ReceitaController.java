package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Receita;
import br.com.dbc.vemser.walletlife.service.ReceitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/receita")
public class ReceitaController {
    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping("/{id}")
    public List<Receita> listarReceitasPorUsuario(@PathVariable Integer id) throws BancoDeDadosException {
        return receitaService.listarById(id);
    }

    @PostMapping
    public ResponseEntity<Void> adicionarReceita(@Valid @RequestBody Receita receita) throws BancoDeDadosException{
        receitaService.adicionarReceita(receita);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idReceita}")
    public ResponseEntity<Receita> editarReceita(@PathVariable("idReceita") Integer id,
                           @Valid @RequestBody Receita receitaAtualizar) throws BancoDeDadosException {
        return new ResponseEntity<>(receitaService.editarReceita(id, receitaAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerReceita(@PathVariable Integer id) throws BancoDeDadosException{
        receitaService.removerReceita(id);
        return ResponseEntity.ok().build();
    }

}
