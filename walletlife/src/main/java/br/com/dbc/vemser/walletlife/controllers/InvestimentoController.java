package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.doc.InvestimentoControllerDoc;
import br.com.dbc.vemser.walletlife.dto.InvestimentoCreateDTO;
import br.com.dbc.vemser.walletlife.dto.InvestimentoDTO;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.service.InvestimentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/investimentos")
public class InvestimentoController implements InvestimentoControllerDoc {

    private final InvestimentoService investimentoService;

    @GetMapping("/{idInvestimento}")
    public ResponseEntity<InvestimentoDTO> buscarInvestimento(@PathVariable("idInvestimento") @Positive Integer id) {
        InvestimentoDTO investimentoDTO = investimentoService.buscarById(id);
        return ResponseEntity.ok(investimentoDTO);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<InvestimentoDTO>> listInvestimentosUsuario(@PathVariable("idUsuario") @Positive Integer id) throws RegraDeNegocioException {
        List<InvestimentoDTO> investimentos = investimentoService.buscarByIdUsuario(id);
        return ResponseEntity.ok(investimentos);
    }

    @PostMapping
    public ResponseEntity<InvestimentoDTO> create(@RequestBody @Valid InvestimentoCreateDTO investimento) throws RegraDeNegocioException {
        InvestimentoDTO novoInvestimento = investimentoService.adicionarInvestimento(investimento);
        return ResponseEntity.status(HttpStatus.OK).body(novoInvestimento);
    }

    @PutMapping("/{idInvestimento}")
    public ResponseEntity<InvestimentoDTO> update(@PathVariable("idInvestimento") @Valid Integer id,
                                                  @RequestBody InvestimentoCreateDTO investimentoAtualizar) throws RegraDeNegocioException {
        InvestimentoDTO investimentoAtualizado = investimentoService.editarInvestimento(id, investimentoAtualizar);
        return ResponseEntity.ok(investimentoAtualizado);
    }

    @DeleteMapping("/{idInvestimento}")
    public ResponseEntity<Void> delete(@PathVariable("idInvestimento") @Positive Integer id) throws Exception {
        investimentoService.removerInvestimento(id);
        return ResponseEntity.noContent().build();
    }
}
