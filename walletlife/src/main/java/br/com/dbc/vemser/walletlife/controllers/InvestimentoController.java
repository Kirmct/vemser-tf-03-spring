package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investimentos") // localhost:8080/investimentos
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @Autowired
    public InvestimentoController(InvestimentoService investimentoService) {
        this.investimentoService = investimentoService;
    }

    @GetMapping("/{idUsuario}") // GET localhost:8080/investimentos/1
    public List<Investimento> list(@PathVariable("idUsuario") Integer id) {
        return investimentoService.listarById(id);
    }


    @PostMapping // POST localhost:8080/investimentos
    public Investimento create(@RequestBody Investimento investimento) {
        return investimentoService.adicionarInvestimento(investimento);
    }

    @PutMapping("/{idInvestimento}") // PUT localhost:8080/investimentos/1
    public Investimento update(@PathVariable("idInvestimento") Integer id,
                          @RequestBody Investimento investimentoAutualizar) throws Exception {

        return investimentoService.editarInvestimento(id, investimentoAutualizar);
    }

    @DeleteMapping("/{idInvestimento}") // DELETE localhost:8080/investimentos/1
    public void delete(@PathVariable("idInvestimento") Integer id) throws Exception {
        investimentoService.removerInvestimento(id);
    }
}
