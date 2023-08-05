package br.com.dbc.vemser.walletlife.controllers;

import br.com.dbc.vemser.walletlife.dto.InvestimentoCreateDTO;
import br.com.dbc.vemser.walletlife.dto.InvestimentoDTO;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.service.InvestimentoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/investimentos") // localhost:8080/investimentos
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @GetMapping("/{idInvestimento}") // GET localhost:8080/investimentos/1
    public InvestimentoDTO buscarInvestimento(@PathVariable("idInvestimento") @Positive Integer id) {
        return investimentoService.buscarById(id);
    }

    @GetMapping("/usuario/{idUsuario}") // GET localhost:8080/investimentos/1
    public List<InvestimentoDTO> listInvestimentosUsuario(@PathVariable("idUsuario") @Positive Integer id) throws RegraDeNegocioException {
        return investimentoService.buscarByIdUsuario(id);
    }


    @PostMapping // POST localhost:8080/investimentos
    public InvestimentoDTO create(@RequestBody InvestimentoCreateDTO investimento) throws RegraDeNegocioException {
        return investimentoService.adicionarInvestimento(investimento);
    }

    @PutMapping("/{idInvestimento}") // PUT localhost:8080/investimentos/1
    public InvestimentoDTO update(@PathVariable("idInvestimento") @Valid Integer id,
                          @RequestBody InvestimentoCreateDTO investimentoAutualizar) throws RegraDeNegocioException {

        return investimentoService.editarInvestimento(id, investimentoAutualizar);
    }

    @DeleteMapping("/{idInvestimento}") // DELETE localhost:8080/investimentos/1
    public void delete(@PathVariable("idInvestimento") @Positive Integer id) throws Exception {
        investimentoService.removerInvestimento(id);
    }
}
