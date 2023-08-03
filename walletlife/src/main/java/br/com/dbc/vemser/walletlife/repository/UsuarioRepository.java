package br.com.dbc.vemser.walletlife.repository;

import br.com.dbc.vemser.walletlife.exceptions.BancoDeDadosException;
import br.com.dbc.vemser.walletlife.modelos.Usuario;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository implements Repositorio<Integer, Usuario> {

    @Override
    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_usuario.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }

    @Override
    public Usuario adicionar(Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Integer proxId = this.getProximoId(con);
            usuario.setId(proxId);

            String sql = "INSERT INTO USUARIO(ID_USUARIO, NOME, DATANASCIMENTO, CPF, EMAIL, SENHA)" +
                    "VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNomeCompleto());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getCpf()); // @TODO Possível erro.
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());

            int res = stmt.executeUpdate();
            return usuario;
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

            String sql = "DELETE FROM USUARIO WHERE iD_USUARIO = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("removerUsuarioPorId.res= " + res);

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
    public Usuario editar(Integer id, Usuario usuario) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE USUARIO SET ");
            sql.append(" nome = ?,");
            sql.append(" dataNascimento = ?, ");
            sql.append(" cpf = ?,");
            sql.append(" email = ?,");
            sql.append(" senha = ?");
            sql.append(" WHERE id_usuario = ?");


            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            System.out.println(usuario.getDataNascimento());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.setInt(6, id);

            // Executa-se a consulta
            int res = stmt.executeUpdate();
            System.out.println("editarUsuario.res=" + res);

            if(res > 0){
                return usuario;
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
    public List<Usuario> listar() throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO";

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_usuario"));
                usuario.setNomeCompleto(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("dataNascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                usuarios.add(usuario);
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
        return usuarios;
    }

    @Override
    public List<Usuario> listarPorId(Integer idUsuario) throws BancoDeDadosException {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM USUARIO WHERE ID_USUARIO = " + idUsuario;

            // Executa-se a consulta
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id_usuario"));
                usuario.setNomeCompleto(res.getString("nome"));
                usuario.setDataNascimento(res.getDate("dataNascimento").toLocalDate());
                usuario.setCpf(res.getString("cpf"));
                usuario.setEmail(res.getString("email"));
                usuario.setSenha(res.getString("senha"));
                usuarios.add(usuario);
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
        return usuarios;
    }
}
