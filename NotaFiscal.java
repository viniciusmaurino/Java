package projetofinal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Timestamp;

public class NotaFiscal extends ConectaMysql{

	private int numero;
	private Date data;
	private float valor;
	private String status;
	
	public NotaFiscal (int num, Date data, float val,String status){
		
		this.numero = num;
		this.data = data;
		this.valor = val;
		this.status = status;
		
	}
	
	public NotaFiscal(){
		numero = 0;
		data = null;
		valor = 0;
		status = "";
	}
	
	public int getNumero(){
		return numero;
	}
	
	public Date getData(){
		return data;
	}
	
	public float getValor(){
		return valor;
	}
	
	public String getStatus(){
		return status;
	}
	
	public boolean cadastraNotaFiscal(NotaFiscal nf){
		boolean notaCadastrada = false;
		try{
			String comandoSQL = "INSERT INTO NOTA_FISCAL VALUES (null, ?, ?, ?);";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setTimestamp(1, new Timestamp(nf.data.getTime())); // <Timestamp> comando para converter a data em milisegundos e armazenar no banco
			ps.setFloat(2, nf.valor);
			ps.setString(3, nf.status);
		
			if(ps.executeUpdate() != 0)
				notaCadastrada = true;

			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return notaCadastrada;
	}
	
	public String buscaNotaFiscal(int num){
		String html = "";
		try{
			String comandoSQL = "SELECT *, DATE_FORMAT(data, '%d/%m/%y') AS dataFormatada, ROUND(valor, 2) AS valor2 FROM NOTA_FISCAL WHERE NUMERO = ?;";
			
			iniciarConexao();
			PreparedStatement ps = conexao.prepareStatement(comandoSQL);

			ps.setInt(1, num);
			ResultSet resultado = ps.executeQuery();
			
			html += "<div class= 'fundoTabelas'>"
					+ "<div class='centro' style= 'width: 955px; margin-left: 110px'>"
						+ "<div class='cabecalho1'> Número </div>"
						+ "<div class='cabecalho2'> Data </div>"
						+ "<div class='cabecalho2'> Valor </div>"
						+ "<div class='cabecalho2'> Status </div>"
						+ "<div class='limpa'></div>";
						
			while(resultado.next()){
				html += "<div class='box1'>" + resultado.getString("numero") + "</div>"; 
				html += "<div class='box2'>" + resultado.getString("dataFormatada") + "</div>";
				html += "<div class='box2'>R$ " + resultado.getString("valor2").replace(".", ",") + "</div>";
				html += "<div class='box2'>" + resultado.getString("status") + "</div>";
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
	
	public String exibeNotaFiscal() {
		
		String html = "";
		try{
			
		String comandoSQL = "SELECT *, DATE_FORMAT(data, '%d/%m/%y') AS dataFormatada, ROUND(valor, 2) AS valor2 FROM NOTA_FISCAL";
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ResultSet resultado = ps.executeQuery(); 
		
		html += "<div class= 'fundoTabelas'>"
				+ "<div class='centro' style='width: 845px; margin-left: 110px'>"
				//+ "<div style='width: 955px'>"
					+ "<div class='cabecalho1'> Número </div>"
					+ "<div class='cabecalho2'> Data </div>"
					+ "<div class='cabecalho2'> Valor </div>"
					+ "<div class='cabecalho2'> Status </div>"
					+ "<div class='limpa'></div>";
			//	+ "</div";
		
		while(resultado.next()){
			//html += "<div style='width: 955px'>";	
				html += "<div class='box1'>" + resultado.getString("numero") + "</div>"; 
				html += "<div class='box2'>" + resultado.getString("dataFormatada") + "</div>";
				html += "<div class='box2'>R$ " + resultado.getString("valor2").replace(".", ",") + "</div>"; //O método replace substitui um caracter da string por outro, sempre que houver, no caso está substituindo o ponto pela vírgula
				html += "<div class='box2'>" + resultado.getString("status") + "</div>";
				html += "<div class='limpa'></div>";
			//html += "</div>";		
		}
		
			html+= "</div>";
		html+= "</div>";
		con.conexao.close();
		
	} catch (Exception exc) { 
		exc.printStackTrace();
		}
		return html;
	}
	
	
	public boolean atualizarNotaFiscal(NotaFiscal nf, int num){
		boolean notaAtualizada = false;
		try{
			
		String comandoSQL = "UPDATE NOTA_FISCAL SET data = ?, valor = ?, status= ? WHERE numero = ?;";
			
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);
			
			ps.setTimestamp(1, new Timestamp(nf.data.getTime()));
			ps.setFloat(2, nf.valor);
			ps.setString(3, nf.status);
			ps.setInt(4, num);
			
			if(ps.executeUpdate() != 0)
				notaAtualizada = true;

			conexao.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return notaAtualizada;
	}
	
	public boolean apagaNotaFiscal(int num){
		boolean notaApagada = false;
		try{
		
		String comandoSQL = "DELETE FROM NOTA_FISCAL WHERE numero = ?;";
		
		iniciarConexao();
		PreparedStatement ps = conexao.prepareStatement(comandoSQL);
		
			ps.setInt(1, num);
			
			if(ps.executeUpdate() != 0){
				notaApagada = true;
			}
			conexao.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return notaApagada;
	}
	
	public String exibeTotalCompradoMes() {
		
		String html = "";
		try{
			
		String comandoSQL = "SELECT ROUND(SUM(VALOR), 2) AS 'VALOR TOTAL' FROM NOTA_FISCAL WHERE DATA <= CURDATE() "
				+ "AND DATA >= DATE_SUB(CURDATE(), INTERVAL 30 DAY);"; // FUNÇÃO ROUND PARA LIMITAR CASAS DECIMAIS
		
		ConectaMysql con = new ConectaMysql();
		con.iniciarConexao();
		
		PreparedStatement ps = con.conexao.prepareStatement(comandoSQL);
		
		ResultSet resultado = ps.executeQuery(); 
		
		html += "<div class= 'fundoTabelas'>"
				+ "<div class='centro' style='width:105px; margin-left: 375px; padding-right: 100px; height: 88px'>"
						+ "<div class='cabecalho2'> Valor Total (R$)</div>"
						+ "<div class='limpa'></div>";
		
		while(resultado.next()){ 	
			html += "<div class='box2'>" + resultado.getString("valor total").replace(".", ",") + "</div>"; 
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



