package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.dto.ReceitaCreateDTO;
import br.com.dbc.vemser.walletlife.dto.ReceitaDTO;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.modelos.Receita;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.service.ReceitaService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@Data
@RequestMapping("/receita")
public class ReceitaController {
    private final ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> listarTodos(){
        return new ResponseEntity<>(receitaService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{idReceita}")
    public ResponseEntity<ReceitaDTO> buscarReceita(@PathVariable("idReceita") @Positive Integer id) throws RegraDeNegocioException{
        return new ResponseEntity<>(receitaService.buscarById(id), HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReceitaDTO>> listarReceitasPorUsuario(@PathVariable("idUsuario") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(receitaService.buscarByIdUsuario(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReceitaDTO> adicionarReceita(@Valid @RequestBody ReceitaCreateDTO receita) throws RegraDeNegocioException {
        return new ResponseEntity<>(receitaService.adicionarReceita(receita), HttpStatus.OK);
    }

    @PutMapping("/{idReceita}")
    public ResponseEntity<ReceitaDTO> editarReceita(@PathVariable("idReceita") Integer id,
                           @Valid @RequestBody ReceitaDTO receitaAtualizar) throws RegraDeNegocioException {
        return new ResponseEntity<>(receitaService.editarReceita(id, receitaAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{idReceita}")
    public ResponseEntity<Void> removerReceita(@PathVariable("idReceita") Integer id) throws RegraDeNegocioException{
        receitaService.removerReceita(id);
        return ResponseEntity.ok().build();
    }

}
