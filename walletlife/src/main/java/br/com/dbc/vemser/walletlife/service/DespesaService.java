package br.com.dbc.vemser.walletlife.service;

import exceptions.BancoDeDadosException;
import modelos.Despesa;
import repository.DespesaRepository;

import java.util.List;

public class DespesaService {

    private DespesaRepository despesaRepository;

    public DespesaService() {
        despesaRepository = new DespesaRepository();
    }

    // criação de um objeto
    public void adicionarDespesa(Despesa despesa) {
        try {

            Despesa despesaAdicionado = despesaRepository.adicionar(despesa);
            System.out.println();
            System.out.println("DESPESA adicionada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
//            System.out.println("TRACE: ");
//            e.printStackTrace();
        }
    }

    // remoção
    public void removerDespesa(Integer id) {
        try {
            boolean conseguiuRemover = despesaRepository.remover(id);
            System.out.println();
            System.out.println("DESPESA removida com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarDespesa(Integer id, Despesa despesa) {
        try {
            despesaRepository.editar(despesa);
            System.out.println();
            System.out.println("DESPESA alterada com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Despesa> listarDespesa(Integer idUsuario) {
        try {
            return despesaRepository.listar(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }


}
