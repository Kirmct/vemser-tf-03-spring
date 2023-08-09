package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.dto.DespesaCreateDTO;
import br.com.dbc.vemser.walletlife.dto.DespesaDTO;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.service.DespesaService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/despesa")
@Slf4j
@Data
public class DespesaController {
    private final DespesaService despesaService;

    @GetMapping //GET localhost:8080/despesa
    public ResponseEntity<List<DespesaDTO>> listarTodos(){
        return new ResponseEntity<>(despesaService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{idDespesa}") //GET localhost:8080/despesa/1
    public ResponseEntity<DespesaDTO> buscarDespesas(@PathVariable("idDespesa") Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(despesaService.buscarById(id), HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")//GET localhost:8080/despesa/usuario/1
    public ResponseEntity<List<DespesaDTO>> listarDespesasPorUsuario(@PathVariable("idUsuario") @Positive Integer id) throws RegraDeNegocioException {
        return new ResponseEntity<>(despesaService.listarDespesaByIdUsuario(id), HttpStatus.OK);
    }

    @PostMapping //POST localhost:8080/despesa
    public ResponseEntity<DespesaDTO> adicionarDespesa(@Valid @RequestBody DespesaCreateDTO despesa) throws RegraDeNegocioException {
        return new ResponseEntity<>(despesaService.adicionarDespesa(despesa), HttpStatus.OK);
    }

    @PutMapping("/{idDespesa}") //PUT localhost:8080/despesa/1
    public ResponseEntity<DespesaDTO> editarDepesa(@PathVariable("idDespesa") Integer id,
                           @Valid @RequestBody DespesaDTO despesaAtualizar) throws RegraDeNegocioException {
                return new ResponseEntity<>(despesaService.editarDespesa(id, despesaAtualizar), HttpStatus.OK);
    }

    @DeleteMapping("/{idDespesa}") //DELETE localhost:8080/despesa/1
    public ResponseEntity<Void> removerDespesa(@PathVariable("idDespesa") Integer id) throws RegraDeNegocioException{
        despesaService.removerDespesa(id);
        return ResponseEntity.ok().build();
    }
}
