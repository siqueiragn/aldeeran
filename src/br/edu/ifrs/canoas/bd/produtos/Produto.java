package br.edu.ifrs.canoas.bd.produtos;

import br.edu.ifrs.canoas.bd.categorias.CategoriaProduto;
import static br.edu.ifrs.canoas.bd.categorias.CategoriaProduto.getLista;

import java.sql.*;
import java.util.ArrayList;

import br.edu.ifrs.canoas.bd.conexao.Conexao;
import br.edu.ifrs.canoas.bd.usuarios.Usuario;

public class Produto {

    private int idItemEstoque;
    private String nome;
    private String descricao;
    private int categoria;
    private double x;
    private double y;
    private double z;

    public Produto() {
    }

    public Produto(int idItemEstoque, String nome, String descricao, int categoria, double x, double y, double z) {
        super();
        this.idItemEstoque = idItemEstoque;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.x = x;
        this.y = y;
        this.z = z;
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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String toString() {
        ArrayList<CategoriaProduto> listProduto = getLista();
        String aux = "";
        for (CategoriaProduto obj : listProduto) {
            if (obj.getIdCategoria() == categoria) {
                aux = obj.getNome();
            }
        }
        return "\n\t[" + idItemEstoque + "]\nNome: " + nome + "\nDescrição: " + descricao + "\nCategoria: " + aux + "\nLocalização: (" + x + "," + y + "," + z + ")";
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
                prod.setX(rs.getDouble("x"));
                prod.setY(rs.getDouble("y"));
                prod.setZ(rs.getDouble("z"));
                lista.add(prod);
            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        c.desconecta();
        return lista;
    }

    public static ArrayList<Produto> getAllProducts() {
        String selectSQL = "SELECT * FROM produto ORDER BY id_item_estoque ASC";
        Conexao c = new Conexao();
        ArrayList<Produto> lista = new ArrayList<>();
        Connection dbConnection = c.getConexao();
        Statement ps;
        try {
            ps = dbConnection.createStatement();
            ResultSet rs = ps.executeQuery(selectSQL);
            while (rs.next()) {
                Produto pdt = new Produto();
                pdt.setIdItemEstoque(rs.getInt("id_item_estoque"));
                pdt.setNome(rs.getString("nome_item"));
                pdt.setCategoria(rs.getInt("categoria"));
                pdt.setDescricao(rs.getString("descricao"));
                pdt.setX(rs.getDouble("x"));
                pdt.setY(rs.getDouble("y"));
                pdt.setZ(rs.getDouble("z"));
                lista.add(pdt);
            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        c.desconecta();
        return lista;
    }

    public static Produto loadID(int id) {
        String selectSQL = "SELECT * FROM produto WHERE id_item_estoque= '" + id + "' ORDER BY id_item_estoque ASC";
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
                prod.setX(rs.getDouble("x"));
                prod.setY(rs.getDouble("y"));
                prod.setZ(rs.getDouble("z"));

            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        c.desconecta();
        return prod;
    }

    public void update(int id, int tipo) {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;
        String insertTableSQL = "";
        try {
            switch (tipo) {
                case 1:
                    insertTableSQL = "UPDATE produto SET nome_item = '?' WHERE id_item_estoque = " + id;
                    pS = dbConnection.prepareStatement(insertTableSQL);
                    pS.setString(1, this.nome);
                    break;
                case 2:
                    insertTableSQL = "UPDATE produto SET descricao = '?' WHERE id_item_estoque = " + id;
                    pS = dbConnection.prepareStatement(insertTableSQL);
                    pS.setString(1, this.descricao);
                    break;
                case 3:
                    insertTableSQL = "UPDATE produto SET categoria = ? WHERE id_item_estoque = " + id;
                    pS = dbConnection.prepareStatement(insertTableSQL);
                    pS.setInt(1, this.categoria);
                    break;
                case 4:
                    insertTableSQL = "UPDATE produto SET x = ? WHERE id_item_estoque = " + id;
                    pS = dbConnection.prepareStatement(insertTableSQL);
                    pS.setDouble(1, this.x);
                    break;
                case 5:
                    insertTableSQL = "UPDATE produto SET y = ? WHERE id_item_estoque = " + id;
                    pS = dbConnection.prepareStatement(insertTableSQL);
                    pS.setDouble(1, this.y);
                    break;
                case 6:
                    insertTableSQL = "UPDATE produto SET z = ? WHERE id_item_estoque = " + id;
                    pS = dbConnection.prepareStatement(insertTableSQL);
                    pS.setDouble(1, z);
                    break;
            }

            pS.executeUpdate();

            c.desconecta();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;
        String insertTSQL = "INSERT INTO produto(nome_item,descricao, categoria, x, y, z) VALUES (?,?,?,?,?,?)";
        try {
            pS = dbConnection.prepareStatement(insertTSQL);
            pS.setString(1, this.nome);
            pS.setString(2, this.descricao);
            pS.setInt(3, this.categoria);
            pS.setDouble(4, this.x);
            pS.setDouble(5, this.z);
            pS.setDouble(6, this.y);
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
