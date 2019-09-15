package projetofinal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FornecedorProduto extends ConectaMysql{

	private int produto_codigo;
	private String  fornecedor_cnpj;
	private int nota_fiscal_numero;
	private int quantidade;
	
	public FornecedorProduto (int pcod, String for_cnpj, int nf_num, int qtde){
		
		this.produto_codigo = pcod;
		this.fornecedor_cnpj = for_cnpj;
		this.nota_fiscal_numero = nf_num;
		this.quantidade = qtde;
		
	}
	
	public FornecedorProduto(){
		nota_fiscal_numero = 0;
		quantidade = 0;
	}
	
	public int getProduto_codigo(){
		return produto_codigo;
	}
	
	public String getFornecedor_cnpj(){
		return fornecedor_cnpj;
	}
	
	public int getNota_fiscal_numero(){
		return nota_fiscal_numero;
	}
	
	public int getQuantidade(){
		return quantidade;
	}
	
	public boolean cadastraFornecedor_produto(FornecedorProduto o){
		boolean fornecedor_produtoCadastrado = false;
		try{
			String comandoSQL = "INSERT INTO FORNECEDOR_PRODUTO VALUES (?, ?, ?, ?);";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setInt(1, o.produto_codigo);
			ps.setString(2, o.fornecedor_cnpj);
			ps.setInt(3, o.nota_fiscal_numero);
			ps.setInt(4, o.quantidade);
		
			

			if(ps.executeUpdate() != 0)
				fornecedor_produtoCadastrado = true;

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return fornecedor_produtoCadastrado;
	}
	
	public String buscaQuantidade(int num){
		String html = "";
		try{
			String comandoSQL = "SELECT FP.*, F.*, P.* FROM FORNECEDOR_PRODUTO FP, FORNECEDOR F, PRODUTO P WHERE NOTA_FISCAL_NUMERO = ? AND cnpj = fornecedor_cnpj AND produto_codigo = codigo;";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setInt(1, num);
			ResultSet resultado = ps.executeQuery();
			
			html += "<div class= 'fundoTabelas'>"
					+ "<div class='centro' style='width:725px; margin-left: 110px; padding-right: 120px' >"
						+ "<div class='cabecalho2'> Produto </div>"
						+ "<div class='cabecalho3'> Fornecedor </div>"
						+ "<div class='cabecalho1'> NF </div>"
						+ "<div class='cabecalho1'> QTDE </div>"
						+ "<div class='limpa'></div>";

			while(resultado.next()){
				html += "<div class='box2'>" + resultado.getString("P.descricao") + "</div>"; 
				html += "<div class='box3'>" + resultado.getString("nome") + "</div>";
				html += "<div class='box1'>" + resultado.getString("nota_fiscal_numero") + "</div>";
				html += "<div class='box1'>" + resultado.getString("quantidade") + "</div>";
			}
			
				html+= "</div>";
			html+= "</div>";

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return html;
	}
	
	/*
	public FornecedorProduto buscaQuantidade(int nota_fiscal_numero){
		FornecedorProduto o = new FornecedorProduto();
		try{
			String comandoSQL = "SELECT * FROM FORNECEDOR_PRODUTO WHERE NOTA_FISCAL_NUMERO = ?;";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setInt(1, nota_fiscal_numero);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				o.produto_codigo = rs.getInt("produto_codigo");
				o.fornecedor_cnpj = rs.getString("fornecedor_cnpj");
				o.nota_fiscal_numero = rs.getInt("nota_fiscal_numero");
				o.quantidade = rs.getInt("quantidade");
					
			}

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return o;
	}
	*/
	
	public boolean atualizarFornecedor_produto(FornecedorProduto o, int nota_fiscal_numero, int produto_codigo){
		boolean fornecedor_produtoAtualizado = false;
		try{
			
		String comandoSQL = "UPDATE fornecedor_produto SET produto_codigo = ?, fornecedor_cnpj = ?, nota_fiscal_numero= ?"
					+ ", quantidade = ? WHERE nota_fiscal_numero = ? AND produto_codigo = ?;";
			
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);
			
			ps.setInt(1, o.produto_codigo);
			ps.setString(2, o.fornecedor_cnpj);
			ps.setInt(3, o.nota_fiscal_numero);
			ps.setInt(4, o.quantidade);
			ps.setInt(5,  nota_fiscal_numero);
			ps.setInt(6,  produto_codigo);
			
			if(ps.executeUpdate() != 0)
				fornecedor_produtoAtualizado = true;

			conexao.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return fornecedor_produtoAtualizado;
	}
	
	public String buscaProduto_codigo() { // método criado para colocar caixa de seleção buscando os valores no banco de dados
		
		String html = "";
		try{
			
		String comandoSQL = "SELECT CODIGO, DESCRICAO FROM PRODUTO ORDER BY CODIGO ASC";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ResultSet resultado = ps.executeQuery(); 
		
		html += "<select name='produto_codigo'>";
		html += "<option>Selecione um produto...</option>";
		while(resultado.next()){
			//<option value="1">1 - CABOS</option>
			html += "<option value='" + resultado.getString("codigo")  + "'>" + resultado.getString("codigo") + " - " + resultado.getString("descricao") + "</option>";
		}
		html += "</select>";
		
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
	}
	
	return html;
}
	
public String buscaFornecedor_cnpj() { // método criado para colocar caixa de seleção buscando os valores no banco de dados
		
		String html = "";
		try{
			
		String comandoSQL = "SELECT CNPJ, NOME FROM FORNECEDOR ORDER BY CNPJ ASC";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ResultSet resultado = ps.executeQuery(); 
		
		html += "<select name='fornecedor_cnpj'>";
		html += "<option>Selecione um fornecedor...</option>";
		while(resultado.next()){
			//<option value="01...-01">01...-01 - MICROSOFT</option>
			html += "<option value='" + resultado.getString("cnpj")  + "'>" + resultado.getString("cnpj") + " - " + resultado.getString("nome") + "</option>";
		}
		html += "</select>";
		
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
	}
	
	return html;
}

public String exibeFornecedoresMaisAtivos() {
	
	String html = "";
	try{
		
	String comandoSQL = "SELECT NOME, COUNT(FORNECEDOR_CNPJ) AS QUANTIDADE FROM FORNECEDOR_PRODUTO FP, FORNECEDOR F WHERE CNPJ = FORNECEDOR_CNPJ GROUP BY FORNECEDOR_CNPJ "
							+ "ORDER BY COUNT(FORNECEDOR_CNPJ) DESC LIMIT 5;";
	
	ConectaMysql con = new ConectaMysql();
	con.iniciarConexao();
	
	PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
	
	ResultSet resultado = ps.executeQuery(); 
	
	html += "<div class= 'fundoTabelas'>"
			+ "<div class='centro' style='width: 521px; margin-left: 215px; padding-right: 219px'>"
					+ "<div class='cabecalho2'> Fornecedor </div>"
					+ "<div class='cabecalho3'> Quantidade de Notas </div>"
					+ "<div class='limpa'></div>";
	
	while(resultado.next()){ 	
		html += "<div class='box2'>" + resultado.getString("nome") + "</div>"; 
		html += "<div class='box3'>" + resultado.getString("quantidade") + "</div>";
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

