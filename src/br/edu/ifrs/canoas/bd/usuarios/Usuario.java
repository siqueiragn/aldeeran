/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canoas.bd.usuarios;

import br.edu.ifrs.canoas.bd.categorias.CategoriaUsuario;
import static br.edu.ifrs.canoas.bd.categorias.CategoriaUsuario.getAll;
import br.edu.ifrs.canoas.bd.conexao.Conexao;
import br.edu.ifrs.canoas.bd.produtos.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Usuario {

    private int idUsuario;
    private String nome;
    private String senha;
    private int tipoUsuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nome, String senha, int tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void insert() {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;
        String insertTSQL = "INSERT INTO usuario (nome,senha,tipo_usuario) VALUES (?,?,?)";
        try {
            pS = dbConnection.prepareStatement(insertTSQL);
            pS.setString(1, this.nome);
            pS.setString(2, this.senha);
            pS.setInt(3, this.tipoUsuario);
            pS.executeUpdate();
            c.desconecta();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(int idUser, int tipo) {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;
        String stringSQL = "";
        try {
            switch (tipo) {
                case 1:
                    stringSQL = "UPDATE usuario SET nome = (?) WHERE id_usuario = " + idUser;
                    pS = dbConnection.prepareStatement(stringSQL);
                    pS.setString(1, this.nome);
                    break;
                case 2:
                    stringSQL = "UPDATE usuario SET senha = (?) WHERE id_usuario = " + idUser;
                    pS = dbConnection.prepareStatement(stringSQL);
                    pS.setString(1, this.senha);
                    break;
                case 3:
                    stringSQL = "UPDATE usuario SET tipo_usuario = ? WHERE id_usuario = " + idUser;
                    pS = dbConnection.prepareStatement(stringSQL);
                    pS.setInt(1, this.tipoUsuario);
                    break;
            }
            pS.executeUpdate();
            c.desconecta();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Conexao C = new Conexao();
        Connection dbConnection = C.getConexao();
        PreparedStatement preparedStatement = null;
        String deleteData = "DELETE FROM usuario WHERE id_usuario= " + id;

        try {
            preparedStatement = dbConnection.prepareStatement(deleteData);

            preparedStatement.executeUpdate();
            C.desconecta();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public static ArrayList<Usuario> getAllUsers() {
        String selectSQL = "SELECT * FROM usuario";
        Conexao c = new Conexao();
        ArrayList<Usuario> lista = new ArrayList<>();
        Connection dbConnection = c.getConexao();
        Statement ps;
        try {
            ps = dbConnection.createStatement();
            ResultSet rs = ps.executeQuery(selectSQL);
            while (rs.next()) {
                Usuario usr = new Usuario();
                usr.setIdUsuario(rs.getInt("id_usuario"));
                usr.setNome(rs.getString("nome"));
                usr.setSenha(rs.getString("senha"));
                usr.setTipoUsuario(rs.getInt("tipo_usuario"));
                lista.add(usr);
            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        c.desconecta();
        return lista;
    }

    @Override
    public String toString() {
        ArrayList<CategoriaUsuario> ctUsr = getAll();
        String aux = "";
        for (CategoriaUsuario ctUsr1 : ctUsr) {
            if (tipoUsuario == ctUsr1.getIdCategoria()) {
                aux += ctUsr1.getNome();
            }
        }
        
        return "\n\t[" + idUsuario + "]\nNome: " + nome + "\nTipo: " + aux;
    }

    public static Usuario loadUser(int id) {
        String selectSQL = "SELECT * FROM usuario WHERE id_usuario= " + id;
        Usuario user = new Usuario();
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        Statement ps;

        try {
            ps = dbConnection.createStatement();
            ResultSet rs = ps.executeQuery(selectSQL);
            while (rs.next()) {
                user.setIdUsuario(rs.getInt("id_usuario"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                user.setTipoUsuario(rs.getInt("tipo_usuario"));

            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        c.desconecta();
        return user;
    }
}