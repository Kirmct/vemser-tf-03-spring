package br.com.dbc.vemser.walletlife.repository;

import br.com.dbc.vemser.walletlife.enumerators.TipoDespesaEReceita;
import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Investimento;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InvestimentoRepository implements Repositorio<Integer, Investimento> {
    @Override
    public Integer getProximoId(Connection connection) throws BancoDeDadosException {
        try {
            String sql = "SELECT seq_investimento.nextval mysequence from DUAL";
            Statement stmt = connection.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            if (res.next()) {
                return res.getInt("mysequence");
            }

            return null;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
    }

    @Override
    public Investimento adicionar(Investimento investimento) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            investimento.setId(proximoId);

            String sql = "INSERT INTO INVESTIMENTO\n" +
                    "(ID_INVESTIMENTO, CORRETORA, TIPO, VALOR, DATA_INICIAL, DESCRICAO, ID_USUARIO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)\n";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, investimento.getId());
            stmt.setString(2, investimento.getCorretora());
            stmt.setString(3, String.valueOf(investimento.getTipo()));
            stmt.setDouble(4, investimento.getValor());
            stmt.setDate(5, Date.valueOf(investimento.getDataInicio()));
            stmt.setString(6, investimento.getDescricao());
            stmt.setInt(7, investimento.getIdFK());

            int res = stmt.executeUpdate();

            if (res > 0){
                return investimento;
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean remover(Integer id) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM INVESTIMENTO WHERE ID_INVESTIMENTO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();

            return res > 0;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Investimento> listar() throws BancoDeDadosException {
        List<Investimento> investimentos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM INVESTIMENTO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(res.getInt("id_investimento"));
                investimento.setCorretora(res.getString("corretora"));

                String tipoInvestimento = res.getString("Tipo").toUpperCase();
                String despesaInvestimento = tipoInvestimento.replaceAll("\u00C1", "A");
                investimento.setTipo(TipoDespesaEReceita.valueOf(despesaInvestimento));

                investimento.setValor(res.getDouble("valor"));
                investimento.setDataInicio(res.getDate("data_inicial").toLocalDate());
                investimento.setDescricao(res.getString("descricao"));
                investimento.setIdFK(res.getInt("id_usuario"));
                investimentos.add(investimento);
            }
            return investimentos;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Investimento editar(Integer id, Investimento investimento) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE INVESTIMENTO SET ");
            sql.append(" valor = ?, ");
            sql.append(" descricao = ?, ");
            sql.append(" corretora = ? ");
            sql.append(" WHERE id_investimento = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setDouble(1, investimento.getValor());
            stmt.setString(2, investimento.getDescricao());
            stmt.setString(3, investimento.getCorretora());
            stmt.setInt(4, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();

            if (res > 0){
                investimento.setId(id);
                return investimento;
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Investimento buscarPorId(Integer idInvestimento) throws BancoDeDadosException {
        Investimento investimento = new Investimento();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM INVESTIMENTO WHERE ID_INVESTIMENTO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idInvestimento);

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                investimento.setId(res.getInt("id_investimento"));
                investimento.setCorretora(res.getString("corretora"));

                String tipoInvestimento = res.getString("Tipo").toUpperCase();
                String despesaInvestimento = tipoInvestimento.replaceAll("\u00C1", "A");
                investimento.setTipo(TipoDespesaEReceita.valueOf(despesaInvestimento));

                investimento.setValor(res.getDouble("valor"));
                investimento.setDataInicio(res.getDate("data_inicial").toLocalDate());
                investimento.setDescricao(res.getString("descricao"));
                investimento.setIdFK(res.getInt("id_usuario"));
            }
            return investimento;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Investimento> listarPorIdUsuario(Integer idUsuario) throws BancoDeDadosException {
        List<Investimento> investimentos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM INVESTIMENTO WHERE ID_USUARIO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idUsuario);

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Investimento investimento = new Investimento();
                investimento.setId(res.getInt("id_investimento"));
                investimento.setCorretora(res.getString("corretora"));

                String tipoInvestimento = res.getString("Tipo").toUpperCase();
                String despesaInvestimento = tipoInvestimento.replaceAll("\u00C1", "A");
                investimento.setTipo(TipoDespesaEReceita.valueOf(despesaInvestimento));

                investimento.setValor(res.getDouble("valor"));
                investimento.setDataInicio(res.getDate("data_inicial").toLocalDate());
                investimento.setDescricao(res.getString("descricao"));
                investimento.setIdFK(res.getInt("id_usuario"));
                investimentos.add(investimento);
            }

            return investimentos;
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
