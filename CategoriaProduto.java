package projetofinal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoriaProduto extends ConectaMysql{
	
	private int codigo;
	private String descricao;
	
	public CategoriaProduto(int cod,String desc){
		
		this.codigo = cod;
		this.descricao = desc;
		
	}
		
	public CategoriaProduto(){
		codigo = 0;
		descricao = "";
	}
		
	public int getCodigo(){
		return codigo;
	}
	public String getDescricao(){
		return descricao;
	}
		
	public boolean cadastraCategoria(CategoriaProduto cp){
		boolean categoriaCadastrada = false;
		try{
			String comandoSQL = "INSERT INTO CATEGORIA_PRODUTO VALUES (null, ?);";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setString(1, cp.descricao.toUpperCase());

			if(ps.executeUpdate() != 0)
				categoriaCadastrada = true;

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return categoriaCadastrada;
	}
	
	public String buscaCategoria(String descricao){
		String html = "";
		try{
			String comandoSQL = "SELECT * FROM CATEGORIA_PRODUTO WHERE descricao LIKE ?;";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setString(1, "%" + descricao + "%");
			ResultSet resultado = ps.executeQuery();
			
			html += "<div class= 'fundoTabelas'>"
					+ "<div class='centro' style='width:435px; margin-left: 274px; padding-right: 246px'>" // a classe "centro" tem comprimento 960px, mas ao colocar esse script com novo comprimento ele sobrecreve o valor padrão da classe
						+ "<div class='cabecalho1'> Código </div>"
						+ "<div class='cabecalho3'> Descrição </div>"
						+ "<div class='limpa'></div>";

			while(resultado.next()){
				html += "<div class='box1'>" + resultado.getString("codigo") + "</div>"; 
				html += "<div class='box3'>" + resultado.getString("descricao") + "</div>";
				html += "<div class='limpa'></div>";
			}
			
			html+= "</div>";
		html+= "</div>";

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return html;
	}
	
	public boolean atualizarCategoria(CategoriaProduto cp, int codigo){
		boolean categoriaAtualizada = false;
		try{
			
		String comandoSQL = "UPDATE CATEGORIA_PRODUTO SET descricao = ? WHERE codigo = ?;";
			
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);
			
			ps.setString(1, cp.descricao.toUpperCase());
			ps.setInt(2, codigo);
			
			if(ps.executeUpdate() != 0)
				categoriaAtualizada = true;

			conexao.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return categoriaAtualizada;
	}
	
	public boolean apagaCategoria(int codigo){
		boolean categoriaApagada = false;
		try{
		
		String comandoSQL = "DELETE FROM CATEGORIA_PRODUTO WHERE codigo = ?;";
		
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);
		
			ps.setInt(1, codigo);
			
			if(ps.executeUpdate() != 0){
				categoriaApagada = true;
			}
			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return categoriaApagada;
	}
	
	public String exibeCategoria() {
		
		String html = "";
		try{
			
		String comandoSQL = "SELECT * FROM CATEGORIA_PRODUTO";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ResultSet resultado = ps.executeQuery(); 
		/*
		html += "<div class='fundo'>"
					+ "<div class= 'topo'></div>"
					+ "<div class= 'interno'>"
						+ "<div class= 'mensagem'> CATEGORIAS DE PRODUTOS</div>"
		*/
						html += "<div class= 'fundoTabelas'>"
							+ "<div class='centro' style='width:435px; margin-left: 274px; padding-right: 246px'>" 
								+ "<div class='cabecalho1'> Código </div>"
								+ "<div class='cabecalho3'> Descrição </div>"
								+ "<div class='limpa'></div>";
						
					
		
		while(resultado.next()){ 
			html += "<div class='box1'>" + resultado.getString("codigo") + "</div>"; 
			html += "<div class='box3'>" + resultado.getString("descricao") + "</div>";
			html += "<div class='limpa'></div>";
		}
					html+= "</div>";
				html+= "</div>";
				
			//html +="</div>";
			//html += "<div class= 'base'></div>";
		//html += "</div>"; 
		
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
	}
	
	return html;
}	
	
public String exibeQuantidadeProdutosPorCategoria() {
		
		String html = "";
		try{
			
		String comandoSQL = "SELECT CATEGORIA_PRODUTO_CODIGO, CATEGORIA_PRODUTO.DESCRICAO, SUM(QUANTIDADE) AS QUANTIDADE "
								+ "FROM PRODUTO, CATEGORIA_PRODUTO WHERE CATEGORIA_PRODUTO_CODIGO = CATEGORIA_PRODUTO.CODIGO "
								+ "GROUP BY CATEGORIA_PRODUTO_CODIGO;";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ResultSet resultado = ps.executeQuery(); 
		/*
		html += "<div class='fundo'>"
					+ "<div class= 'topo'></div>"
					+ "<div class= 'interno'>"
						+ "<div class= 'mensagem'> CATEGORIAS DE PRODUTOS</div>"
		*/
						html += "<div class= 'fundoTabelas'>"
							+ "<div class='centro' style='width:823px; margin-left: 74px; padding-right: 58px'>" 
								+ "<div class='cabecalho2'> Cód Categoria </div>"
								+ "<div class='cabecalho3'> Descrição Categoria </div>"
								+ "<div class='cabecalho3'> Quantidade de Produtos </div>"
								+ "<div class='limpa'></div>";
						
					
		
		while(resultado.next()){ 
			html += "<div class='box2'>" + resultado.getString("categoria_produto_codigo") + "</div>"; // aqui usa os nomes dos campos do banco de dados que estão no Select
			html += "<div class='box3'>" + resultado.getString("descricao") + "</div>";
			html += "<div class='box3'>" + resultado.getString("quantidade") + "</div>";
			html += "<div class='limpa'></div>";
		}
					html+= "</div>";
				html+= "</div>";
				
			//html +="</div>";
			//html += "<div class= 'base'></div>";
		//html += "</div>"; 
		
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
	}
	
	return html;
}	
	
}

