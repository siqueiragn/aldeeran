/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.canos.bd.relacoes;

import br.edu.ifrs.canoas.bd.conexao.Conexao;
import br.edu.ifrs.canoas.bd.usuarios.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDateTime;

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

    public static MovimentarEstoque loadRetirado(int idProd) {
        String selectSQL = "SELECT * FROM retirado_usuario_estoque WHERE id_item_estoque = " + idProd +" ORDER BY id_item_estoque DSC LIMIT 1";
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        Statement ps;
        MovimentarEstoque mEst = new MovimentarEstoque();

        try {
            ps = dbConnection.createStatement();
            ResultSet rs = ps.executeQuery(selectSQL);
            while (rs.next()) {
                mEst.setIdProduto(rs.getInt("id_item_estoque"));
                mEst.setIdUsuario(rs.getInt("id_usuario"));
                mEst.setHorarioDevolucao(rs.getString("data_devolucao"));
                mEst.setHorarioRetirado(rs.getString("data_retirada"));
             
            }

        } catch (SQLException f) {
            f.printStackTrace();
        }
        c.desconecta();
        return mEst;
    }
    public LocalDateTime getData(){
        return null;
        
    }

    public void retirarProduto() {
        Conexao c = new Conexao();
        Connection dbConnection = c.getConexao();
        PreparedStatement pS = null;

        String insertTSQL = "INSERT INTO retirado_usuario_estoque(id_usuario, id_usuario_estoque) VALUES (?,?)";
        try {
            pS = dbConnection.prepareStatement(insertTSQL);
            pS.setInt(1, this.idUsuario);
            pS.setInt(2, this.idProduto);
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
        String updateSQL = "SELECT CURRENT_DATE";
    }

}
