package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/investimentos") // localhost:8080/investimentos
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @Autowired
    public InvestimentoController(InvestimentoService investimentoService) {
        this.investimentoService = investimentoService;
    }

    @GetMapping("/{idUsuario}") // GET localhost:8080/investimentos/1
    public Investimento list(@PathVariable("idUsuario") @Positive Integer id) {
        return investimentoService.listarById(id);
    }


    @PostMapping // POST localhost:8080/investimentos
    public Investimento create(@RequestBody @Valid Investimento investimento) throws RegraDeNegocioException {
        return investimentoService.adicionarInvestimento(investimento);
    }

    @PutMapping("/{idInvestimento}") // PUT localhost:8080/investimentos/1
    public Investimento update(@PathVariable("idInvestimento") @Valid Integer id,
                          @RequestBody Investimento investimentoAutualizar) throws Exception {

        return investimentoService.editarInvestimento(id, investimentoAutualizar);
    }

    @DeleteMapping("/{idInvestimento}") // DELETE localhost:8080/investimentos/1
    public void delete(@PathVariable("idInvestimento") @Positive Integer id) throws Exception {
        investimentoService.removerInvestimento(id);
    }
}
