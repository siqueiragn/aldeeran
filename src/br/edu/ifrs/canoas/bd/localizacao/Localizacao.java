package br.edu.ifrs.canoas.bd.localizacao;

import java.sql.*;

import br.edu.ifrs.canoas.bd.conexao.Conexao;

public class Localizacao {
private int idLocalizacao;
private Double x;
private Double y;
private Double z;

public Localizacao(){}
public Localizacao(int idLocalizacao, Double x, Double y, Double z) {
	this.idLocalizacao = idLocalizacao;
	this.x = x;
	this.y = y;
	this.z = z;
}
public int getIdLocalizacao() {
	return idLocalizacao;
}
public void setIdLocalizacao(int idLocalizacao) {
	this.idLocalizacao = idLocalizacao;
}
public Double getX() {
	return x;
}
public void setX(Double x) {
	this.x = x;
}
public Double getY() {
	return y;
}
public void setY(Double y) {
	this.y = y;
}
public Double getZ() {
	return z;
}
public void setZ(Double z) {
	this.z = z;
}

	public Localizacao load(int id) {
		String selectSQL = "SELECT * FROM localizacao WHERE id_localizacao= '" + id + "'";
		Localizacao local = new Localizacao();
		Conexao c = new Conexao();
		Connection dbConnection = c.getConexao();
		Statement ps;
		try {
			ps = dbConnection.createStatement();
			ResultSet rs = ps.executeQuery(selectSQL);
			while (rs.next()) {
				local.setIdLocalizacao(rs.getInt("id_localizacao"));
				local.setX(rs.getDouble("x"));
				local.setY(rs.getDouble("y"));
				local.setZ(rs.getDouble("z"));
				
			}

		} catch (SQLException f) {
			f.printStackTrace();
		}
		return local;
	}


	public void update(int id) {
		Conexao c = new Conexao();
		Connection dbConnection = c.getConexao();
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "UPDATE localizacao SET x = ?, y = ?, z = ? WHERE id = ?";
		try {
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setDouble(1, this.idLocalizacao);
			preparedStatement.setDouble(2, this.x);
			preparedStatement.setDouble(3, this.y);
			preparedStatement.setDouble(4, this.z);

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
		String insertTSQL = "INSERT INTO localizacao(id_localizacao, x, y, z) VALUES (?,?,?,?)";
		try {
			pS = dbConnection.prepareStatement(insertTSQL);
			pS.setInt(1, this.idLocalizacao);
			pS.setDouble(2, this.x);
			pS.setDouble(3, this.y);
			pS.setDouble(4, this.z);
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
		String deleteData = "DELETE FROM localizacao WHERE id_localizacao = "+id;

		try {
			preparedStatement = dbConnection.prepareStatement(deleteData);
			
			preparedStatement.executeUpdate();
			C.desconecta();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}