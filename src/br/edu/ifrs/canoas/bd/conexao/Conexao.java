package br.edu.ifrs.canoas.bd.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Marcio Bigolin <marcio.bigolin@canoas.ifrs.edu.br>
 * @version 1.0.0
 */

public class Conexao {
	private String usuario = "gabriel_nunes";
	private String senha = "98187625";
	private String banco = "alderaan";
	private String servidor = "webacademico.canoas.ifrs.edu.br" ;
	private int porta = 5432;
	
	private Connection conexao = null;
	
	
	
	public Conexao(){}//inicia com os valores padrões
	
	public Conexao(	String banco, 
					String usuario, 
					String senha){
		this.banco = banco;
		this.senha = senha;
		this.usuario = usuario;
	}
	
	public Connection getConexao(){
		if(conexao == null){
			try {
				Class.forName("org.postgresql.Driver");
				conexao = DriverManager.getConnection(
		                    "jdbc:postgresql://" + this.servidor + ":" + this.porta + "/" + this.banco, 
		                    		this.usuario, 
		                    		this.senha);
		      
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
           
		}
		return conexao;
	}
	
	public void desconecta(){
		try {
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
