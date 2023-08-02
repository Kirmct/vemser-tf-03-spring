package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.repository.InvestimentoRepository;

import java.util.List;

public class InvestimentoService {
    private InvestimentoRepository investimentoRepository;

    public InvestimentoService() {
        investimentoRepository = new InvestimentoRepository();
    }

    // criação de um objeto
    public void adicionarInvestimento(Investimento investimento) {
        try {
            Investimento investimentoAdicionado = investimentoRepository.adicionar(investimento);
            System.out.println();
            System.out.println("INVESTIMENTO adicionado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    // remoção
    public void removerInvestimento(Integer id) {
        try {
            investimentoRepository.remover(id);
            System.out.println();

            System.out.println("INVESTIMENTO removido com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public void editarInvestimento(Integer id, Investimento investimento) {
        try {
            investimentoRepository.editar(investimento);
            System.out.println();
            System.out.println("INVESTIMENTO alterado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // leitura
    public List<Investimento> listar(Integer idUsuario) {
        try {
            return investimentoRepository.listar(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

}
