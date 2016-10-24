package br.edu.ifrs.canoas.bd.produtos;

import java.sql.*;
import java.util.ArrayList;

import br.edu.ifrs.canoas.bd.conexao.Conexao;

public class Produto {

    private int idItemEstoque;
    private String nome;
    private String descricao;
    private int localizacao;
    private int categoria;

    public Produto() {
    }

    public Produto(int idItemEstoque, String nome, String descricao, int localizacao, int categoria) {
        super();
        this.idItemEstoque = idItemEstoque;
        this.nome = nome;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.categoria = categoria;
    }

    public int getIdItemEstoque() {
        return idItemEstoque;
    }

    public void setIdItemEstoque(int idItemEstoque) {
        this.idItemEstoque = idItemEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Produto> load(String nome) {
        String selectSQL = "SELECT * FROM produto WHERE nome_item= '" + nome + "'";
        Produto prod = new Produto();
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        Statement ps;
        ArrayList<Produto> lista = new ArrayList<>();
        try {
            ps = dbConnection.createStatement();
            ResultSet rs = ps.executeQuery(selectSQL);
            while (rs.next()) {
                prod.setIdItemEstoque(rs.getInt("id_item_estoque"));
                prod.setNome(rs.getString("nome_item"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setCategoria(rs.getInt("categoria"));
                prod.setLocalizacao(rs.getInt("localizacao"));
                lista.add(prod);
            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        return lista;
    }

    public Produto loadID(int id) {
        String selectSQL = "SELECT * FROM produto WHERE id_item_estoque= '" + id + "'";
        Produto prod = new Produto();
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        Statement ps;

        try {
            ps = dbConnection.createStatement();
            ResultSet rs = ps.executeQuery(selectSQL);
            while (rs.next()) {
                prod.setIdItemEstoque(rs.getInt("id_item_estoque"));
                prod.setNome(rs.getString("nome_item"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setCategoria(rs.getInt("categoria"));
                prod.setLocalizacao(rs.getInt("localizacao"));

            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        return prod;
    }

    public void update(int id) {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "UPDATE produto SET nome_item = '?', descricao = '?', localizacao = ?, categoria = ? WHERE id = ?";
        try {
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, this.nome);
            preparedStatement.setString(2, this.descricao);
            preparedStatement.setInt(3, this.localizacao);
            preparedStatement.setInt(4, this.categoria);

            preparedStatement.executeUpdate();

            c.desconecta();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;
        String insertTSQL = "INSERT INTO produto(nome_item,descricao,localizacao, categoria) VALUES (?,?,?,?)";
        try {
            pS = dbConnection.prepareStatement(insertTSQL);
            pS.setString(1, this.nome);
            pS.setString(2, this.descricao);
            pS.setInt(3, this.localizacao);
            pS.setInt(4, this.categoria);
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
        String deleteData = "DELETE FROM produto WHERE id_item_estoque = " + id;

        try {
            preparedStatement = dbConnection.prepareStatement(deleteData);

            preparedStatement.executeUpdate();
            C.desconecta();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

}
