package projetofinal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConectaMysql {
	
	// Atributos (variáveis) para armazenar valores e configurações
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
				Class.forName(driver); // passando qual o driver será utilizado na classe para conexão com o banco de dados
				
				// O método "getConnection" realiza a conexão com o banco a partir dos parâmetros passados, caso ele conecte,
					// vamos atribuir o seu retorno ao nosso objeto "conexão"
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
