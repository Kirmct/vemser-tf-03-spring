package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Despesa;
import br.com.dbc.vemser.walletlife.modelos.Receita;
import br.com.dbc.vemser.walletlife.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    private DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
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
    public Despesa editarDespesa(Integer id, Despesa despesa) {
        try {
            Despesa despesaAtualizada =despesaRepository.editar(id, despesa);
            System.out.println();
            System.out.println("DESPESA alterada com sucesso!");
            return despesaAtualizada;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }

    // leitura
    public List<Despesa> listarDespesa(Integer idUsuario) {
        try {
            return despesaRepository.listarPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }


}
