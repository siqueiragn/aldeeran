/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canos.bd.relacoes;

import br.edu.ifrs.canoas.bd.conexao.Conexao;
import br.edu.ifrs.canoas.bd.produtos.Produto;
import static br.edu.ifrs.canoas.bd.produtos.Produto.loadID;
import br.edu.ifrs.canoas.bd.usuarios.Usuario;
import static br.edu.ifrs.canoas.bd.usuarios.Usuario.loadUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author Gabriel
 */
public class MovimentarEstoque {

    private int idUsuario;
    private int idProduto;
    private String horarioRetirado;
    private String horarioDevolucao;

    public MovimentarEstoque() {
    }

    public MovimentarEstoque(int idUsuario, int idProduto, String horarioRetirado, String horarioDevolucao) {
        this.idUsuario = idUsuario;
        this.idProduto = idProduto;
        this.horarioDevolucao = horarioDevolucao;
        this.horarioRetirado = horarioRetirado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getHorarioRetirado() {
        return horarioRetirado;
    }

    public void setHorarioRetirado(String horarioRetirado) {
        this.horarioRetirado = horarioRetirado;
    }

    public String getHorarioDevolucao() {
        return horarioDevolucao;
    }

    public void setHorarioDevolucao(String horarioDevolucao) {
        this.horarioDevolucao = horarioDevolucao;
    }

    public String toString() {
        Usuario user;
        user = loadUser(idUsuario);
        Produto prod;
        prod = loadID(idProduto);
        String aux;
        if (horarioDevolucao != null) {
            aux = " e devolveu na data: " + horarioDevolucao;
        } else {
            aux = "";
        }
        return "\n" + user.getNome() + " retirou o produto: " + prod.getNome() + " na data: " + horarioRetirado + aux;

    }


    public static ArrayList<MovimentarEstoque> loadRetirado(int idUser) {
        String selectSQL = "SELECT * FROM retirado_usuario_estoque WHERE id_usuario = " + idUser + " ORDER BY id_item_estoque DESC";
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        Statement ps;

        ArrayList<MovimentarEstoque> lista = new ArrayList<>();
        try {
            ps = dbConnection.createStatement();
            ResultSet rs = ps.executeQuery(selectSQL);
            while (rs.next()) {
                MovimentarEstoque mEst = new MovimentarEstoque();
                mEst.setIdProduto(rs.getInt("id_item_estoque"));
                mEst.setIdUsuario(rs.getInt("id_usuario"));
                mEst.setHorarioDevolucao(rs.getString("data_devolucao"));
                mEst.setHorarioRetirado(rs.getString("data_retirada"));
                lista.add(mEst);
            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        c.desconecta();
        return lista;
    }

    public void retirarProduto() {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;
        String insertTSQL = "INSERT INTO retirado_usuario_estoque(id_usuario, id_item_estoque, data_retirada) VALUES (?,?,?)";
        try {
            pS = dbConnection.prepareStatement(insertTSQL);
            pS.setInt(1, this.idUsuario);
            pS.setInt(2, this.idProduto);
            pS.setString(3, this.horarioRetirado);
            pS.executeUpdate();
            c.desconecta();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void devolverProduto() {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;
        String updateSQL = "UPDATE retirado_usuario_estoque SET data_devolucao = '?' WHERE id_usuario = ? AND id_item_estoque = ?";
        try {
            pS = dbConnection.prepareStatement(updateSQL);
            pS.setString(1, this.horarioDevolucao);
            pS.setInt(2, this.idUsuario);
            pS.setInt(3, this.idProduto);
            pS.executeUpdate();
            c.desconecta();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
