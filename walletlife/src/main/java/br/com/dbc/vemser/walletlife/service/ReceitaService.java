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
    public Receita editarReceita(Integer id, Receita receita) {
        try {
            Receita conseguiuEditar = receitaRepository.editar(id, receita);
            System.out.println();
            System.out.println("Alteração realizada com sucesso!");
            return conseguiuEditar;
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
            return null;
        }
    }

    // leitura
    public List<Receita> listarById(Integer idUsuario) {
        try {
            return receitaRepository.listarPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
