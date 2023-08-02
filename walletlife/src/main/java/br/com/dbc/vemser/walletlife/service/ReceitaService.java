package br.com.dbc.vemser.walletlife.service;

import exceptions.BancoDeDadosException;
import modelos.Receita;
import repository.ReceitaRepository;

import java.util.List;

public class ReceitaService {

    private ReceitaRepository receitaRepository;

    public ReceitaService() {
        receitaRepository = new ReceitaRepository();
    }

    // criação de um objeto
    public void adicionarReceita(Receita receita) {
        try {
            Receita receitaAdicionado = receitaRepository.adicionar(receita);
            System.out.println("\nReceita adicinada com sucesso!" +
                    "\nBanco: " + receitaAdicionado.getBanco() +
                    "\nEmpresa:  " + receitaAdicionado.getEmpresa() +
                    "\nValor: " + receitaAdicionado.getValor() +
                    "\nDescrição: " + receitaAdicionado.getDescricao());
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    // remoção
    public void removerReceita(Integer id) {
        try {
            boolean conseguiuRemover = receitaRepository.remover(id);
            System.out.println();
            System.out.println("Removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarReceita(Integer id, Receita receita) {
        try {
            boolean conseguiuEditar = receitaRepository.editar(receita);
            System.out.println();
            System.out.println("Alteração realizada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Receita> listar(Integer idUsuario) {
        try {
            return receitaRepository.listar(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
