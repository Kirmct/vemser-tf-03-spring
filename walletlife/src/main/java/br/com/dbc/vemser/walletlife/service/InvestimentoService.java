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
            Usuario usuarioById = usuarioService.listarPessoasPorId(investimento.getIdFK());

            if (usuarioById != null){
                Investimento investimentoAdicionado = investimentoRepository.adicionar(investimento);

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
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
    }

    // atualização de um objeto
    public Investimento editarInvestimento(Integer id, Investimento investimento) throws RegraDeNegocioException  {
        try {
            Usuario usuarioById = usuarioService.listarPessoasPorId(investimento.getIdFK());

            if (usuarioById != null){
                Investimento investimentoAtualizado = investimentoRepository.editar(id, investimento);

                return investimentoAtualizado;
            }else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura por id do investimento
    public Investimento buscarById(Integer idInvestimento) {
        try {
            return investimentoRepository.buscarPorId(idInvestimento);
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // leitura geral
    public List<Investimento> listarTodos() {
        try {
            return investimentoRepository.listar();
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leitura por usuario
    public List<Investimento> buscarByIdUsuario(Integer idUsuario) throws RegraDeNegocioException   {
        try {
            Usuario usuarioById = usuarioService.listarPessoasPorId(idUsuario);

            if (usuarioById != null){
                return investimentoRepository.listarPorIdUsuario(idUsuario);
            }else {
                throw new RegraDeNegocioException("Usuario não encontrado");
            }
        } catch (BancoDeDadosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
