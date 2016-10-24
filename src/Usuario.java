import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.edu.ifrs.canoas.bd.conexao.Conexao;

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
	
	public Usuario load(String nome) {
		String selectSQL = "SELECT * FROM usuario WHERE nome= '" + nome+"'";
		Usuario usr = new Usuario();
		Conexao c = new Conexao();
		Connection dbConnection = c.getConexao();
		Statement ps;
		try {
			ps = dbConnection.createStatement();
			ResultSet rs = ps.executeQuery(selectSQL);
			while (rs.next()) {
				usr.setIdUsuario(rs.getInt("id_digital"));
				usr.setNome(rs.getString("nome"));
				usr.setSenha(rs.getString("senha"));
				usr.setTipoUsuario(rs.getInt("tipo_usuario"));	
			}
			

		} catch (SQLException f) {
			f.printStackTrace();
		}
		return usr;
	}
	
	public void update(int idUsuario) {
		Conexao c = new Conexao();
		Connection dbConnection = c.getConexao();
		PreparedStatement preparedStatement = null;
		String insertTableSQL = "UPDATE usuario SET nome = ?, tipo_usuario = ?, senha = ? WHERE id_usuario = ?";
		try {
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, this.nome);
			preparedStatement.setInt(2, this.tipoUsuario);
			preparedStatement.setString(3, this.senha);
			preparedStatement.setInt(4, idUsuario);

			preparedStatement.executeUpdate();

			c.desconecta();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(){
		Conexao c = new Conexao();
		Connection dbConnection = c.getConexao();
		PreparedStatement pS = null;
		String insertTSQL = "INSERT INTO usuario(nome,senha,tipo_usuario) VALUES (?,?,?)";
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
	
	public void delete(int idUsuario) {
		Conexao C = new Conexao();
		Connection dbConnection = C.getConexao();
		PreparedStatement preparedStatement = null;
		String deleteData = "DELETE FROM usuario WHERE id_usuario = (?)";

		try {
			preparedStatement = dbConnection.prepareStatement(deleteData);
			preparedStatement.setInt(1, idUsuario);
			preparedStatement.executeUpdate();
			C.desconecta();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	

}
