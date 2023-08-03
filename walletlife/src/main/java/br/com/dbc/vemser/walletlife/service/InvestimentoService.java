package br.com.dbc.vemser.walletlife.service;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.exceptions.RegraDeNegocioException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import br.com.dbc.vemser.walletlife.repository.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestimentoService {
    private InvestimentoRepository investimentoRepository;
    private UsuarioService usuarioService;

    public InvestimentoService(InvestimentoRepository investimentoRepository, UsuarioService usuarioService) {
        this.investimentoRepository = investimentoRepository;
        this.usuarioService = usuarioService;
    }

    // criação de um objeto
    public Investimento adicionarInvestimento(Investimento investimento) throws RegraDeNegocioException {
        try {
            List<Usuario> usuarioById = usuarioService.listarPessoasPorId(investimento.getIdFK());

            if (!usuarioById.isEmpty()){
                Investimento investimentoAdicionado = investimentoRepository.adicionar(investimento);

                System.out.println();
                System.out.println("INVESTIMENTO adicionado com sucesso!");
                return investimento;
            }else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
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
    public Investimento editarInvestimento(Integer id, Investimento investimento) {
        try {
            investimentoRepository.editar(id, investimento);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return investimento;
    }

    // leitura por id
    public List<Investimento> listarById(Integer idUsuario) {
        try {
            return investimentoRepository.listarPorId(idUsuario);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura por id
    public List<Investimento> listarTodos(Integer idUsuario) {
        try {
            return investimentoRepository.listar();
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
