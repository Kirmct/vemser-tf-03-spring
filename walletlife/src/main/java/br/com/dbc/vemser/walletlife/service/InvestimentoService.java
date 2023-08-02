package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestimentoService {
    private InvestimentoRepository investimentoRepository;

    public InvestimentoService(InvestimentoRepository investimentoRepository) {
        this.investimentoRepository = investimentoRepository;
    }

    // criação de um objeto
    public Investimento adicionarInvestimento(Investimento investimento) {
        try {
            Investimento investimentoAdicionado = investimentoRepository.adicionar(investimento);
            System.out.println();
            System.out.println("INVESTIMENTO adicionado com sucesso!");
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
        return investimento;
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
