package projetofinal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Fornecedor extends ConectaMysql{

	private String cnpj;
	private String nome;
	private String razao_social;
	private String endereco;
	private String email;
	private String telefone;
	private String ativo;
	
	public Fornecedor (String cnpj, String nome,String rsocial,String end,String email,String tel, String situacao){
		
		this.cnpj = cnpj;
		this.nome = nome;
		razao_social = rsocial;
		endereco = end;
		this.email = email;
		telefone = tel;
		ativo = situacao;
		
	}
	
	public Fornecedor(){
		cnpj = "";
		nome = "";
		razao_social = "";
		endereco = "";
		email = "";
		telefone = "";
		ativo="";
	}
	
	public String getCnpj(){
		return cnpj;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getRazaoSocial(){
		return razao_social;
	}
	
	public String getEndereco(){
		return endereco;
	}
	
	public String getEmail(){
		return email;
	}
	public String getTelefone(){
		return telefone;
	}
	public String getAtivo(){
		return ativo;
	}

	
	public boolean cadastraFornecedor(Fornecedor f){
		boolean fornecedorCadastrado = false;
		try{
			String comandoSQL = "INSERT INTO FORNECEDOR VALUES (?, ?, ?, ?, ?, ?, ?);";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setString(1, f.cnpj);
			ps.setString(2, f.nome.toUpperCase());
			ps.setString(3, f.razao_social.toUpperCase());
			ps.setString(4, f.endereco.toUpperCase());
			ps.setString(5, f.email);
			ps.setString(6,  f.telefone);
			ps.setString(7, f.ativo.toUpperCase());

			if(ps.executeUpdate() != 0)
				fornecedorCadastrado = true;

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return fornecedorCadastrado;
	}
	
	public String buscaFornecedor(String nome){
		String html = "";
		try{
			String comandoSQL = "SELECT * FROM FORNECEDOR WHERE NOME LIKE ?;";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setString(1, "%" + nome + "%");
			ResultSet resultado = ps.executeQuery();

			html += "<div class= 'fundoTabelas'>"
					+ "<div class='centro'>"
						+ "<div style='width: 1620px'>"
							+ "<div class='cabecalho2'> CNPJ </div>"
							+ "<div class='cabecalho2'> Nome Fantasia </div>"
							+ "<div class='cabecalho3'> Razão Social </div>"
							+ "<div class='cabecalho3'> Endereço </div>"
							+ "<div class='cabecalho3'> Email </div>"
							+ "<div class='cabecalho2'> Telefone </div>"
							+ "<div class='cabecalho1'> Ativo </div>"
							+ "<div class='limpa'></div>"
						+ "</div>";
			
			while(resultado.next()){
				
			html += "<div style='width: 1620px'>";	
				html += "<div class='box2'>" + resultado.getString("cnpj") + "</div>"; 
				html += "<div class='box2'>" + resultado.getString("nome") + "</div>";
				html += "<div class='box3'>" + resultado.getString("razao_social") + "</div>";
				html += "<div class='box3'>" + resultado.getString("endereco") + "</div>";
				html += "<div class='box3'>" + resultado.getString("email") + "</div>";
				html += "<div class='box2'>" + resultado.getString("telefone") + "</div>";
				html += "<div class='box1'>" + resultado.getString("ativo") + "</div>";
				html += "<div class='limpa'></div>";
			html += "</div>";	
			}
			
			html+= "</div>";
			html+= "</div>";

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return html;
	}
	
	public String exibeFornecedor() {
		
		String html = "";
		try{
			
		String comandoSQL = "SELECT * FROM FORNECEDOR";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ResultSet resultado = ps.executeQuery();
		
		html += "<div class= 'fundoTabelas'>"
				+ "<div class='centro'>"
					+ "<div style='width: 1620px'>"
						+ "<div class='cabecalho2'> CNPJ </div>"
						+ "<div class='cabecalho2'> Nome Fantasia </div>"
						+ "<div class='cabecalho3'> Razão Social </div>"
						+ "<div class='cabecalho3'> Endereço </div>"
						+ "<div class='cabecalho3'> Email </div>"
						+ "<div class='cabecalho2'> Telefone </div>"
						+ "<div class='cabecalho1'> Ativo </div>"
						+ "<div class='limpa'></div>"
					+ "</div>";
		
		while(resultado.next()){
			
			/* modo simples (sem formatação de tabelas)
			html += "<div class='cnpj'>CNPJ: " + resultado.getString("cnpj") + "</div>"; 
			html += "<div class='nome'>Nome Fantasia: " + resultado.getString("nome") + "</div>";
			html += "<div class='razao_social'>Razão Social: " + resultado.getString("razao_social") + "</div>";
			html += "<div class='endereco'>Endereço: " + resultado.getString("endereco") + "</div>";
			html += "<div class='email'>Email: " + resultado.getString("email") + "</div>";
			html += "<div class='telefone'>Telefone: " + resultado.getString("telefone") + "</div>";
			html += "<div class='ativo'>Ativo: " + resultado.getString("ativo") + "</div>";
			*/
			
		html += "<div style='width: 1620px'>";	
			html += "<div class='box2'>" + resultado.getString("cnpj") + "</div>"; 
			html += "<div class='box2'>" + resultado.getString("nome") + "</div>";
			html += "<div class='box3'>" + resultado.getString("razao_social") + "</div>";
			html += "<div class='box3'>" + resultado.getString("endereco") + "</div>";
			html += "<div class='box3'>" + resultado.getString("email") + "</div>";
			html += "<div class='box2'>" + resultado.getString("telefone") + "</div>";
			html += "<div class='box1'>" + resultado.getString("ativo") + "</div>";
			html += "<div class='limpa'></div>";
		html += "</div>";	
		}
		
		html+= "</div>";
		html+= "</div>";
		
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
		}
		return html;
	}
	
	
	public boolean atualizarFornecedor(Fornecedor f, String cnpj){
		boolean fornecedorAtualizado = false;
		try{
			
		String comandoSQL = "UPDATE fornecedor SET cnpj = ?, nome = ?, razao_social = ?, endereco= ? , email= ?"
					+ ", telefone = ?, ativo = ? WHERE cnpj = ?;";
			
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);
			
		ps.setString(1, f.cnpj);	
		ps.setString(2, f.nome.toUpperCase());
		ps.setString(3, f.razao_social.toUpperCase());
		ps.setString(4, f.endereco.toUpperCase());
		ps.setString(5, f.email);
		ps.setString(6,  f.telefone);
		ps.setString(7, f.ativo.toUpperCase());
		ps.setString(8, cnpj);
			
			if(ps.executeUpdate() != 0)
				fornecedorAtualizado = true;

			conexao.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return fornecedorAtualizado;
	}
	
	/*
	public boolean apagaFornecedor(String cnpj){
		boolean fornecedorApagado = false;
		try{
		
		String comandoSQL = "DELETE FROM fornecedor WHERE cnpj = ?;";
		
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);
		
			ps.setString(1, cnpj);
			
			if(ps.executeUpdate() != 0){
				fornecedorApagado = true;
			}
			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fornecedorApagado;
	}
	*/
}


