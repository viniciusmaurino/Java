package projetofinal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

	public class Produto extends ConectaMysql{
		
		private int codigo;
		private String descricao;
		private int quantidade;
		private float preco;
		private String local_estoque_codigo;
		private int categoria_produto_codigo;
		
		public Produto(int cod,String desc,int qtde,float preco,String estoque_cod,int cat_cod){
			
			this.codigo = cod;
			this.descricao = desc;
			this.quantidade = qtde;
			this.preco = preco;
			this.local_estoque_codigo = estoque_cod;
			this.categoria_produto_codigo = cat_cod;
			
		}
				
		public Produto(){
			codigo = 0;
			descricao = "";
			quantidade = 0;
			preco = 0;
			//local_estoque_codigo = ""; --> feito com caixa de seleção, que foi criada no método <buscaLocalEstoque>
			//categoria_produto_codigo = 0; --> feito com caixa de seleção, que foi criada no método <buscaCategoriaProduto>
		}
		
		public int getCodigo(){
			return codigo;
		}
		public String getDescricao(){
			return descricao;
		}
		public int getQuantidade(){
			return quantidade;
		}
		public float getPreco(){
			return preco;
		}
		public String getLocal_estoque_codigo(){
			return local_estoque_codigo;
		}
		public int getCategoria_produto_codigo(){
			return categoria_produto_codigo;
		}
		
		public boolean cadastraProduto(Produto p){
			boolean produtoCadastrado = false;
			try{
				String comandoSQL = "INSERT INTO produto VALUES (null, ?, ?, ?, ?, ?);";
				
				iniciarConexao();
				PreparedStatement ps = conexao.prepareStatement(comandoSQL);

				ps.setString(1, p.descricao.toUpperCase()); // método para converter o que foi digitado para Maiúsculas
				ps.setInt(2, p.quantidade);
				ps.setFloat(3, p.preco);
				ps.setString(4, p.local_estoque_codigo.toUpperCase());
				ps.setInt(5, p.categoria_produto_codigo);
				
				if(ps.executeUpdate() != 0)
					produtoCadastrado = true;

				conexao.close();
			}catch(Exception e){
				e.printStackTrace();
			}

			return produtoCadastrado;
		}
		
		public String buscaProduto(String descricao){
			String html = "";
			try{
				String comandoSQL = "SELECT P.*, C.*, ROUND(preco, 2) AS preco2 FROM produto P, categoria_produto C WHERE P.descricao LIKE ?;";
				
				iniciarConexao();
				PreparedStatement ps = conexao.prepareStatement(comandoSQL);

				ps.setString(1, "%" + descricao + "%");
				ResultSet resultado = ps.executeQuery();
				
				html += "<div class= 'fundoTabelas'>"
						+ "<div class='centro'>"
							+ "<div style='width: 1117px'>"
								+ "<div class='cabecalho1'> Código </div>"
								+ "<div class='cabecalho2'> Descrição </div>"
								+ "<div class='cabecalho2'> Qtde em Estoque </div>"
								+ "<div class='cabecalho2'> Valor Unitário (R$) </div>"
								+ "<div class='cabecalho2'> Local Estoque </div>"
								+ "<div class='cabecalho2'> Categoria </div>"
								+ "<div class='limpa'></div>"
							+ "</div>";

				while(resultado.next()){
				html += "<div style='width: 1117px'>";		
					html += "<div class='box1'>" + resultado.getString("P.codigo") + "</div>"; 
					html += "<div class='box2'>" + resultado.getString("P.descricao") + "</div>";
					html += "<div class='box2'>" + resultado.getString("P.quantidade") + "</div>";
					html += "<div class='box2'>" + resultado.getString("preco2").replace(".", ",") + "</div>";
					html += "<div class='box2'>" + resultado.getString("P.local_estoque_codigo") + "</div>";
					html += "<div class='box2'>" + resultado.getString("C.descricao") + "</div>";
					//html += "<div class='box2'>" + resultado.getString("categoria_produto_codigo") + "</div>";
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
		
		public boolean atualizarProduto(Produto p, int codigo){
			boolean produtoAtualizado = false;
			try{
				
			String comandoSQL = "UPDATE produto SET descricao = ?, quantidade = ?, preco = ? , local_estoque_codigo = ?"
						+ ", categoria_produto_codigo = ? WHERE codigo = ?;";
				
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);
				
				ps.setString(1, p.descricao.toUpperCase());
				ps.setInt(2, p.quantidade);
				ps.setFloat(3, p.preco);
				ps.setString(4, p.local_estoque_codigo.toUpperCase());
				ps.setInt(5, p.categoria_produto_codigo);
				ps.setInt(6, codigo);
				
				if(ps.executeUpdate() != 0)
					produtoAtualizado = true;

				conexao.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			return produtoAtualizado;
		}

		/*
		public boolean apagaProduto(int codigo){
			boolean produtoApagado = false;
			try{
			
			String comandoSQL = "DELETE FROM produto WHERE codigo = ?;";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);
			
				ps.setInt(1, codigo);
				
				
				if(ps.executeUpdate() != 0){
					produtoApagado = true;
				}
				conexao.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return produtoApagado;
		}
		*/
		
		public String exibeProduto() {
			
			String html = "";
			try{
				
			String comandoSQL = "SELECT P.*, C.*, ROUND(preco, 2) AS preco2 FROM PRODUTO P, CATEGORIA_PRODUTO C WHERE categoria_produto_codigo = C.codigo";
			
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultado = ps.executeQuery(); 
			
			html += "<div class= 'fundoTabelas'>"
					+ "<div class='centro'>"
						+ "<div style='width: 1117px'>"
							+ "<div class='cabecalho1'> Código </div>"
							+ "<div class='cabecalho2'> Descrição </div>"
							+ "<div class='cabecalho2'> Qtde em Estoque </div>"
							+ "<div class='cabecalho2'> Valor Unitário (R$) </div>"
							+ "<div class='cabecalho2'> Local Estoque </div>"
							+ "<div class='cabecalho2'> Categoria </div>"
							+ "<div class='limpa'></div>"
						+ "</div>";
			
			while(resultado.next()){ 
			html += "<div style='width: 1117px'>";		
				html += "<div class='box1'>" + resultado.getString("P.codigo") + "</div>"; 
				html += "<div class='box2'>" + resultado.getString("P.descricao") + "</div>";
				html += "<div class='box2'>" + resultado.getString("P.quantidade") + "</div>";
				html += "<div class='box2'>" + resultado.getString("preco2").replace(".", ",") + "</div>";
				html += "<div class='box2'>" + resultado.getString("P.local_estoque_codigo") + "</div>";
				html += "<div class='box2'>" + resultado.getString("C.descricao") + "</div>";
				//html += "<div class='box2'>" + resultado.getString("categoria_produto_codigo") + "</div>";
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
		
		public String buscaLocalEstoque() { // método criado para colocar caixa de seleção buscando os valores no banco de dados
			
			String html = "";
			try{
				
			String comandoSQL = "SELECT CODIGO FROM LOCAL_ESTOQUE ORDER BY CODIGO ASC";
			
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultado = ps.executeQuery(); 
			
			html += "<select name='codEstoq'>";
			html += "<option>Selecione um local de estoque...</option>";
			while(resultado.next()){
				//<option value="A5P3">A5P3</option>
				html += "<option value='" + resultado.getString("codigo")  + "'>" + resultado.getString("codigo") + "</option>";
			}
			html += "</select>";
			
			con.conexao.close();
			
		} catch (Exception exc) { 
			exc.printStackTrace();
		}
		
		return html;
	}
		
		public String buscaCategoriaProduto() { // método criado para colocar caixa de seleção buscando os valores no banco de dados
			
			String html = "";
			try{
				
			String comandoSQL = "SELECT CODIGO, DESCRICAO FROM CATEGORIA_PRODUTO ORDER BY CODIGO ASC";
			
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultado = ps.executeQuery(); 
			
			html += "<select name='categoria'>";
			html += "<option>Selecione uma categoria...</option>";
			while(resultado.next()){
				//<option value="1">1 - ACESSORIOS</option>
				html += "<option value='" + resultado.getString("codigo")  + "'>" + resultado.getString("codigo") + " - " + resultado.getString("descricao") + "</option>";
			}
			html += "</select>";
			
			con.conexao.close();
			
		} catch (Exception exc) { 
			exc.printStackTrace();
		}
		
		return html;
	}
		
		
public String exibeProdutoQuantidadeBaixa() {
			
			String html = "";
			try{
				
			String comandoSQL = "SELECT CODIGO, DESCRICAO, QUANTIDADE, ROUND(PRECO, 2) AS preco2 FROM PRODUTO WHERE QUANTIDADE < 10;";
			
			ConectaMysql con = new ConectaMysql();
			con.iniciarConexao();
			
			PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
			
			ResultSet resultado = ps.executeQuery(); 
			
			html += "<div class= 'fundoTabelas'>"
					+ "<div class='centro' style='width:725px; margin-left: 125px; padding-right: 105px'>"
							+ "<div class='cabecalho1'> Código </div>"
							+ "<div class='cabecalho2'> Descrição </div>"
							+ "<div class='cabecalho2'> Qtde em Estoque </div>"
							+ "<div class='cabecalho2'> Valor Unitário (R$) </div>"
							+ "<div class='limpa'></div>";
			
			while(resultado.next()){ 	
				html += "<div class='box1'>" + resultado.getString("codigo") + "</div>"; 
				html += "<div class='box2'>" + resultado.getString("descricao") + "</div>";
				html += "<div class='box2'>" + resultado.getString("quantidade") + "</div>";
				html += "<div class='box2'>" + resultado.getString("preco2").replace(".", ",") + "</div>";	
			}
			
			html+= "</div>";
			html+= "</div>";
			
			con.conexao.close();
			
		} catch (Exception exc) { 
			exc.printStackTrace();
		}
		
		return html;
	}
		
}


