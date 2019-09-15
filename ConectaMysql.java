package projetofinal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaMysql {
	
	// Atributos (vari�veis) para armazenar valores e configura��es
		private String usuario = "root";
		private String senha = "Senai1234";
		private String driver = "com.mysql.jdbc.Driver";
		
						// protocolo : porta : // caminho / base de dados
					   // Java DataBase Connection : 3306 : // servidor local
		private String caminho = "jdbc:mysql://localhost/projeto_estoque";
		
		public Connection conexao;
		
		public boolean iniciarConexao() {
			boolean conectado = false;
			
			try {
				Class.forName(driver); // passando qual o driver ser� utilizado na classe para conex�o com o banco de dados
				
				// O m�todo "getConnection" realiza a conex�o com o banco a partir dos par�metros passados, caso ele conecte,
					// vamos atribuir o seu retorno ao nosso objeto "conex�o"
				conexao = DriverManager.getConnection(caminho, usuario, senha);
				
				// Se a linha de cima executou corretamente
				conectado = true;
				
			} catch (Exception exc) {
				// Caso foi capturado algum erro, imprimimos a mensagem de erro
				exc.printStackTrace();
			}
			
			return conectado;
		}

}
