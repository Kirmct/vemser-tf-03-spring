package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Receita;
import br.com.dbc.vemser.walletlife.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    // criação de um objeto
    public void adicionarReceita(Receita receita) throws BancoDeDadosException {
        Receita receitaAdicionado = receitaRepository.adicionar(receita);
        System.out.println("\nReceita adicinada com sucesso!" +
                "\nBanco: " + receitaAdicionado.getBanco() +
                "\nEmpresa:  " + receitaAdicionado.getEmpresa() +
                "\nValor: " + receitaAdicionado.getValor() +
                "\nDescrição: " + receitaAdicionado.getDescricao());

    }

    // remoção
    public void removerReceita(Integer id) throws BancoDeDadosException {
        boolean conseguiuRemover = receitaRepository.remover(id);
        System.out.println();
        System.out.println("Removido com sucesso!");
    }

    // atualização de um objeto
    public Receita editarReceita(Integer id, Receita receita) throws BancoDeDadosException {
        Receita conseguiuEditar = receitaRepository.editar(id, receita);
        System.out.println();
        System.out.println("Alteração realizada com sucesso!");
        return conseguiuEditar;
    }

    // leitura
    public Receita listarById(Integer idUsuario) throws BancoDeDadosException {
        return receitaRepository.listarPorId(idUsuario);
    }
}
