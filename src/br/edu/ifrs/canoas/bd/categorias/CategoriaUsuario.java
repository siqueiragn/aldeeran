package br.edu.ifrs.canoas.bd.categorias;

import java.sql.*;
import java.util.ArrayList;

import br.edu.ifrs.canoas.bd.conexao.Conexao;

public class CategoriaUsuario {
private int idCategoria;
private String nome;
private String descricao;

public CategoriaUsuario(){}

public CategoriaUsuario(int idCategoria, String nome, String descricao) {

	this.idCategoria = idCategoria;
	this.nome = nome;
	this.descricao = descricao;
}

public int getIdCategoria() {
	return idCategoria;
}

public void setIdCategoria(int idCategoria) {
	this.idCategoria = idCategoria;
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

public CategoriaUsuario load(String nome) {
	String selectSQL = "SELECT * FROM categoria_usuario WHERE nome = '" + nome + "'";
	CategoriaUsuario usrCat = new CategoriaUsuario();
	Conexao c = new Conexao();
	Connection dbConnection = c.getConexao();
	Statement ps;
	try {
		ps = dbConnection.createStatement();
		ResultSet rs = ps.executeQuery(selectSQL);
		while (rs.next()) {
			usrCat.setIdCategoria(rs.getInt("id_categoria"));
			usrCat.setNome(rs.getString("nome"));
			usrCat.setDescricao(rs.getString("descricao"));
		}

	} catch (SQLException f) {
		f.printStackTrace();
	}
        c.desconecta();
	return usrCat;
}

public static ArrayList<CategoriaUsuario> getAll() {
	String selectSQL = "SELECT * FROM categoria_usuario";
	Conexao c = new Conexao();
	ArrayList<CategoriaUsuario> lista = new ArrayList<>();
	 
	Connection dbConnection = c.getConexao();
	Statement ps;
	try {
		ps = dbConnection.createStatement();
		ResultSet rs = ps.executeQuery(selectSQL);
		while (rs.next()) {
                        CategoriaUsuario usrCat = new CategoriaUsuario();
			usrCat.setIdCategoria(rs.getInt("id_categoria"));
			usrCat.setNome(rs.getString("nome"));
			usrCat.setDescricao(rs.getString("descricao"));
			lista.add(usrCat);
		}

	} catch (SQLException f) {
		f.printStackTrace();
	}
        c.desconecta();
	return lista;
}

public void update(int id) {
	Conexao c = new Conexao();
	Connection dbConnection = c.getConexao();
	PreparedStatement preparedStatement = null;
	String insertTableSQL = "UPDATE categoria_usuario SET nome = '?', descricao = '?' WHERE id_categoria = (?)";
	try {
		preparedStatement = dbConnection.prepareStatement(insertTableSQL);
		preparedStatement.setString(1, this.nome);
		preparedStatement.setString(2, this.descricao);
		preparedStatement.setInt(3, this.idCategoria);

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
	String insertTSQL = "INSERT INTO categoria_usuario(nome, descricao) VALUES (?,?)";
	try {
		pS = dbConnection.prepareStatement(insertTSQL);
		pS.setString(1, this.nome);
		pS.setString(2, this.descricao);
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
	String deleteData = "DELETE FROM categoria_usuario WHERE id_categoria= "+id;

	try {
		preparedStatement = dbConnection.prepareStatement(deleteData);
		
		preparedStatement.executeUpdate();
		C.desconecta();
	} catch (SQLException e2) {
		e2.printStackTrace();
	}
}
}
