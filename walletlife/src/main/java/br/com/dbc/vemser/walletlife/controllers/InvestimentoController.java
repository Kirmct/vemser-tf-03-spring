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

    @GetMapping // GET localhost:8080/investimentos
    public List<Investimento> list() {
        return investimentoService.listar(1);
    }

//    @GetMapping("/byid") // GET localhost:8080/pessoa/byid?id=1
//    public List<Contato> listByName(@RequestParam("id") Integer id) {
//        return contatoService.listById(id);
//    }
//
//    @PostMapping // POST localhost:8080/contato
//    public Contato create(@RequestBody Contato contato) {
//        return contatoService.create(contato);
//    }
//
//    @PutMapping("/{idContato}") // PUT localhost:8080/contato/1
//    public Contato update(@PathVariable("idContato") Integer id,
//                          @RequestBody Contato contatoAtualizar) throws Exception {
//
//        return contatoService.update(id, contatoAtualizar);
//    }
//
//    @DeleteMapping("/{idContato}") // DELETE localhost:8080/contato/1
//    public void delete(@PathVariable("idContato") Integer id) throws Exception {
//        contatoService.delete(id);
//    }
}
