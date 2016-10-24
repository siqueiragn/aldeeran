package br.edu.ifrs.canoas.bd.categorias;
import br.edu.ifrs.canoas.bd.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class CategoriaProduto {
    private int idCategoria;
    private String nome;
    private String descricao;
    
    public CategoriaProduto(){}
    public CategoriaProduto(int idCategoria, String nome, String descricao){
        this.idCategoria = idCategoria;
        this.descricao = descricao;
        this.nome = nome;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public ArrayList<CategoriaProduto> getLista(){
       String selectSQL = "SELECT * FROM categoria_produto";
		
		Conexao c = new Conexao();
		Connection dbConnection = c.getConexao();
		Statement ps;
                CategoriaProduto catProd ;
                ArrayList<CategoriaProduto> listProd = new ArrayList<>();
		try {
			ps = dbConnection.createStatement();
			ResultSet rs = ps.executeQuery(selectSQL);
			while (rs.next()) {
                            catProd = new CategoriaProduto();
                            catProd.setIdCategoria(rs.getInt("id_categoria"));
                            catProd.setNome(rs.getString("nome"));
                            catProd.setDescricao(rs.getString("descricao"));
                            listProd.add(catProd);
                        }

		} catch (SQLException f) {
			                 System.out.println("TÁ COM ERRO CLASSE CATEGORIAPRODUTO");
		}
		return listProd;
	}

    
}
