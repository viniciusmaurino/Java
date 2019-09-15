package projetofinal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import projetofinal.ConectaMysql;


public class Usuario extends ConectaMysql{
	
	// Atributos
	private String matricula;
	private String senha;
	private String nivel_acesso;
	
	//Construtor vazio (serve, no caso, para ao abrir a página em html e irão aparecer os campos, eles estarão vazios, dessa
		// forma iria aparecer <null> em todos eles, mas fazendo este método os campos vazios aparecerão em branco
	public Usuario(){
		matricula = "";
		senha = "";
		nivel_acesso = "";
	}
	
	public Usuario(String matricula, String senha, String nivel){
		this.matricula = matricula;
		this.senha = senha;
		nivel_acesso = nivel;
	}
	
	// <get> retorna o valor do campo; serve para acessar, em outra classe, um atributo que foi declarado aqui como privado; 
	// <set> atribui um valor ao campo;
	
	public String getMatricula(){ 
		return matricula;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getNivelAcesso() {
		return nivel_acesso;
	}
	
	public boolean cadastraUsuario(String mat, String password, String na) {
		boolean usuarioCadastrado = false;
		try {
			
			String comandoSQL = "INSERT INTO USUARIO VALUES (?, ?, ?)";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);
			
			ps.setString(1, mat); 
			ps.setString(2, password);  
			ps.setString(3, na); 
			
			if(ps.executeUpdate() != 0){
				usuarioCadastrado = true;
			}
			
			conexao.close();
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return usuarioCadastrado;
	}

	
	public boolean removeUsuario(String mat) {
		boolean usuarioRemovido = false;
		try {
			
			String comandoSQL = "DELETE FROM USUARIO WHERE MATRICULA = ?; ";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);
			
			ps.setInt(1, Integer.parseInt(mat)); 
			
			if(ps.executeUpdate() != 0){
				usuarioRemovido = true;
			}
			
			conexao.close();
			
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return usuarioRemovido;
	}

	public boolean atualizarUsuario(String mat, String password, String na, String senha_antiga){
		boolean usuarioAtualizado = false;
		try{
			
		String comandoSQL = "UPDATE USUARIO SET matricula = ?, senha = ?, nivel_acesso = ? WHERE SENHA = ?;";
		
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setString(1, mat);
			ps.setString(2, password);
			ps.setString(3, na);
			ps.setString(4, senha_antiga);

			if(ps.executeUpdate() != 0){
				usuarioAtualizado = true;
			}
			conexao.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return usuarioAtualizado;
	}
	
	public String exibeUsuario() {
			//Usuario u = new Usuario();
			String html = "";
			try{
				
			String comandoSQL = "SELECT * FROM USUARIO";
			
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultado = ps.executeQuery(); 
			
			html += "<div class= 'fundoTabelas'>"
					+ "<div class='centro' style='width:435px; margin-left: 270px; padding-right: 250px'>" 
						+ "<div class='cabecalho2'> Matrícula </div>"
						//+ "<div class='cabecalho2'> Senha </div>"
						+ "<div class='cabecalho2'> Tipo </div>"
						+ "<div class='limpa'></div>";
			
			while(resultado.next()){ 
				html += "<div class='box2'>" + resultado.getString("matricula") + "</div>"; 
				//html += "<div class='box2'>" + resultado.getString("senha") + "</div>";
				html += "<div class='box2'>" + resultado.getString("nivel_acesso") + "</div>";
				html += "<div class='limpa'></div>";
			}
			
			html+= "</div>";
			html+= "</div>";
			
			con.conexao.close();
			
		} catch (Exception exc) { 
			exc.printStackTrace();
		}
		
		return html;
	}
			
	public String buscaUsuario(String mat) {
		String html = "";
		
		try{
			
		String comandoSQL = "SELECT * FROM USUARIO WHERE MATRICULA = ?";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ps.setInt(1, Integer.parseInt(mat));
		
		ResultSet resultado = ps.executeQuery(); 
		
		html += "<div class= 'fundoTabelas'>"
				+ "<div class='centro' style='width:435px; margin-left: 270px; padding-right: 250px'>" 
					+ "<div class='cabecalho2'> Matrícula </div>"
					+ "<div class='cabecalho2'> Tipo </div>"
					+ "<div class='limpa'></div>";
		
		while(resultado.next()){ 
			html += "<div class='box2'>" + resultado.getString("matricula") + "</div>"; 
			html += "<div class='box2'>" + resultado.getString("nivel_acesso") + "</div>";
			html += "<div class='limpa'></div>";
		}
		
		html+= "</div>";
		html+= "</div>";
		
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
	}
	
	return html;
}
	
	public Usuario buscaUsuario2(String mat) {
		Usuario u = new Usuario();
		
		try{
			
		String comandoSQL = "SELECT * FROM USUARIO WHERE MATRICULA = ?";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ps.setInt(1, Integer.parseInt(mat));
		
		ResultSet resultado = ps.executeQuery(); 
		
		while(resultado.next()){ 
			u.matricula = resultado.getString("matricula");
			u.senha = resultado.getString("senha");  
			u.nivel_acesso = resultado.getString("nivel_acesso");
		}
		
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
	}
	
	return u;
}
	
	public boolean executaLogin(String mat, String senha) {
		
		boolean usuarioLogado = false;
		
		try{
			
			String comandoSQL = "SELECT SENHA FROM USUARIO WHERE MATRICULA = ?";
			
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ps.setString(1, mat);
			
			ResultSet resultado = ps.executeQuery(); 
			
			while(resultado.next()){ 
				if(senha.equals(resultado.getString("senha"))){
					usuarioLogado = true;
				}
			}
			
			con.conexao.close();
			
		} catch (Exception exc) { 
			exc.printStackTrace();
		}
		
		return usuarioLogado;
		
	}
	
}
