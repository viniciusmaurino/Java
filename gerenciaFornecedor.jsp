<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function limpaCampo2(){
		document.getElementById("cnpj").value = "";
		document.getElementById("nome").value = "";
		document.getElementById("rs").value = "";
		document.getElementById("end").value = "";
		document.getElementById("email").value = "";
		document.getElementById("tel").value = "";
	}
</script>
</head>
<body>
	<%!
		projetofinal.Fornecedor f = new projetofinal.Fornecedor();
	%>
	
	<% 
	
	if (request.getParameter("voltar") != null) {
		response.sendRedirect("menu.jsp");
	}
	
	if (request.getParameter("cadastrar") != null) {
		String cnpj = request.getParameter("cnpj");
		String nome = request.getParameter("nome");
		String rs = request.getParameter("rs");
		String end = request.getParameter("end");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String ativo = request.getParameter("ativo");
		
		f = new projetofinal.Fornecedor(cnpj, nome, rs, end, email, tel, ativo);
		if (f.cadastraFornecedor(f) == true) {
			out.print("<script>alert('Fornecedor cadastrado com sucesso!')</script>");
		}
	}
	
	/*
	if (request.getParameter("buscar") != null) {
		String nome = request.getParameter("nome");
		out.print(f.buscaFornecedor(nome));
	}
	*/
	
	/**
	if (request.getParameter("remover") != null) {
		String cnpj = request.getParameter("cnpj");
		
		if (f.apagaFornecedor(cnpj) == true) {
			out.print("<script>alert('Fornecedor removido com sucesso!')</script>");
		}
	}
	**/
	
	if (request.getParameter("atualizar") != null) {
		String cnpj = request.getParameter("cnpj");
		String nome = request.getParameter("nome");
		String rs = request.getParameter("rs");
		String end = request.getParameter("end");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String ativo = request.getParameter("ativo");
		
		f = new projetofinal.Fornecedor(cnpj, nome, rs, end, email, tel, ativo);
		if (f.atualizarFornecedor(f, cnpj) == true) {
			out.print("<script>alert('Dados alterados com sucesso!')</script>");
		}
	}
	
	/*
	if (request.getParameter("listar") != null) {
		out.print(f.exibeFornecedor());
	}
	*/
	
	%>
	
	<div class='fundo'>
		<div class= 'topo'></div>
			<div class= 'interno'>
				<div class= 'mensagem'> FORNECEDORES</div>
		
	<%
		if (request.getParameter("buscar") != null) {
			String nome = request.getParameter("nome");
			out.print(f.buscaFornecedor(nome));
		}
	
		if (request.getParameter("listar") != null) {
			out.print(f.exibeFornecedor());
		}
	%>
	
	<div class= 'fundoBotao'>
				<form action="gerenciaFornecedor.jsp" method="get">
				
				
					<div class= 'esquerdaMenor' style='height: 279px'>
						<span>CNPJ:</span><br/>
						<span>Nome Fantasia:</span><br/>
						<span>Razão Social:</span><br/>
						<span>Endereço:</span><br/>
						<span>Email:</span><br/>
						<span>Telefone:</span><br/>
						<span>Ativo:</span><br/>
						
					</div>
						
					<div class= 'direita' style='height: 279px'>
					
						<input type="text" name="cnpj" id="cnpj" value="<%=f.getCnpj() %>"><br/>
						<input type="text" name="nome" id="nome"value="<%=f.getNome() %>"><br/>
						<input type="text" name="rs" id="rs" value="<%=f.getRazaoSocial() %>"><br/>
						<input type="text" name="end" id="end" value="<%=f.getEndereco() %>"><br/>
						<input type="text" name="email" id="email" value="<%=f.getEmail() %>"><br/>
						<input type="text" name="tel" id="tel" value="<%=f.getTelefone() %>"><br/>
						<select name="ativo">
							<option value="SIM">SIM</option>
							<option value="NAO">NÃO</option>
						</select><br/>
					
						<br/><br/><br/>
					</div>	
		
		<br/><br/>
		
		<input type="submit" name="atualizar" value="Atualizar">
		<input type="submit" name="buscar" value="Buscar" title="A busca deve ser realizada pelo Nome do fornecedor">
		<input type="submit" name="cadastrar" value="Cadastrar">
		<input type="submit" name="listar" value="Listar">
		<input type="submit" name="voltar" value="Voltar">
		<!-- <input type="submit" name="remover" value="Remover"> -->
		
	</form>
	
	</div>
	
		</div> <!-- FECHA INTERNO  -->
		<div class= 'base'></div>
	</div> <!-- FECHA FUNDO  -->
	
	<script type="text/javascript">limpaCampo2();</script>
	
</body>
</html>