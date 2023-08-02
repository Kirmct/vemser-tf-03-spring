package br.com.dbc.vemser.walletlife.repository;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Receita;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaRepository implements Repositorio<Integer, Receita> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_receita.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Receita adicionar(Receita receita) throws BancoDeDadosException {
        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoId = this.getProximoId(con);
            receita.setId(proximoId);

            String sql = "INSERT INTO RECEITA\n" +
                    "(ID_RECEITA, BANCO, EMPRESA, VALOR, DESCRICAO, ID_USUARIO)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)\n";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, receita.getId());
            stmt.setString(2, receita.getBanco());
            stmt.setString(3, receita.getEmpresa());
            stmt.setDouble(4, receita.getValor());
            stmt.setString(5, receita.getDescricao());
            stmt.setInt(6, receita.getIdFK());

            int res = stmt.executeUpdate();
            return receita;
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
    public boolean remover(Integer id) throws BancoDeDadosException {

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM RECEITA WHERE id_receita = ?";

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
    public boolean editar(Receita receita) throws BancoDeDadosException {

        Connection con = null;

        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE RECEITA SET ");
            sql.append(" valor = ?, ");
            sql.append(" descricao = ? ");
            sql.append(" WHERE ID_USUARIO = ?");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setDouble(1, receita.getValor());
            stmt.setString(2, receita.getDescricao());
            stmt.setInt(3, receita.getIdFK());

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
    public List<Receita> listar(Integer id) throws BancoDeDadosException {

        List<Receita> receitas = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM RECEITA WHERE ID_USUARIO = " + id;

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Receita receita = new Receita();
                receita.setId(res.getInt("id_receita"));
                receita.setBanco(res.getString("banco"));
                receita.setEmpresa(res.getString("empresa"));
                receita.setValor(res.getDouble("valor"));
                receita.setDescricao(res.getString("descricao"));
                receita.setIdFK(res.getInt("id_usuario"));
                receitas.add(receita);
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
        return receitas;
    }
}
