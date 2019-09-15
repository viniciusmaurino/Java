<%@page import="projetofinal.Produto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="estilos.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function limpaCampo(){
		if(document.getElementById("codigo").value == 0) {
			document.getElementById("codigo").value = "";
		}
		if(document.getElementById("qtde").value == 0) {
			document.getElementById("qtde").value = "";
		}
		if(document.getElementById("preco").value == 0) {
			document.getElementById("preco").value = "";
		}
	}
	
	function limpaCampo2(){
		document.getElementById("codigo").value = "";
		document.getElementById("descricao").value = "";
		document.getElementById("qtde").value = "";
		document.getElementById("preco").value = "";
	}
</script>
</head>
<body>
	<%!
		projetofinal.Produto p = new projetofinal.Produto();
	%>
	
	<%
	
	if (request.getParameter("voltar") != null) {
		response.sendRedirect("menu.jsp");
	}
	
		if (request.getParameter("cadastrar") != null) {
			String descricao = request.getParameter("descricao");
			String qtde = request.getParameter("qtde");
			String preco = request.getParameter("preco");
			String codEstoq = request.getParameter("codEstoq");
			String categoria = request.getParameter("categoria");
			
			p = new projetofinal.Produto(0, descricao, Integer.parseInt(qtde), Float.parseFloat(preco), 
					codEstoq, Integer.parseInt(categoria));
			if (p.cadastraProduto(p) == true) {
				out.print("<script>alert('Produto cadastrado com sucesso!')</script>");
			}
		}
	
		/*
		if (request.getParameter("buscar") != null) {
			String descricao = request.getParameter("descricao");
			
			out.print(p.buscaProduto(descricao));
		}
		*/
		
		/**
		if (request.getParameter("remover") != null) {
			String codigo = request.getParameter("codigo");
			
			if (p.apagaProduto(Integer.parseInt(codigo)) == true) {
				out.print("<script>alert('Produto removido com sucesso!')</script>");
			}
		}
		**/
		
		if (request.getParameter("atualizar") != null) {
			String codigo = request.getParameter("codigo");
			String descricao = request.getParameter("descricao");
			String qtde = request.getParameter("qtde");
			String preco = request.getParameter("preco");
			String codEstoq = request.getParameter("codEstoq");
			String categoria = request.getParameter("categoria");
			
			p = new projetofinal.Produto(Integer.parseInt(codigo), descricao, Integer.parseInt(qtde), Float.parseFloat(preco), 
					codEstoq, Integer.parseInt(categoria));
			if (p.atualizarProduto(p, Integer.parseInt(codigo)) == true) {
				out.print("<script>alert('Dados alterados com sucesso!')</script>");
			}
		}
		
		/*
		if (request.getParameter("listar") != null) {
			out.print(p.exibeProduto());
		}
		*/
	
	%>
	
	<div class='fundo'>
	<div class= 'topo'></div>
		<div class= 'interno'>
			<div class= 'mensagem'> PRODUTOS</div>
	
	<%
		if (request.getParameter("buscar") != null) {
			String descricao = request.getParameter("descricao");
		
			out.print(p.buscaProduto(descricao));
		}
		if (request.getParameter("listar") != null) {
			out.print(p.exibeProduto());
		}
		if (request.getParameter("relatorio") != null) {
			out.print(p.exibeProdutoQuantidadeBaixa());
		}
	%>
	
	<div class= 'fundoBotao'>
				<form action="gerenciaProduto.jsp" method="get">
				
				
					<div class= 'esquerdaMenor' style='height: 279px'>
						<span>Código do Produto:</span><br/>
						<span>Descrição:</span><br/>
						<span>Qtde em Estoque:</span><br/>
						<span>Valor Unitário: R$</span><br/>
						<span>Local de Estoque:</span><br/>
						<span>Categoria:</span><br/>
						
					</div>
						
					<div class= 'direita' style='height: 279px'>
					
						<input type="text" name="codigo" id="codigo" onfocus="javascript:limpaCampo()" value="<%=p.getCodigo() %>"><br/>
						<input type="text" name="descricao" id="descricao" value="<%=p.getDescricao() %>"><br/>
						<input type="text" name="qtde" id="qtde" onfocus="javascript:limpaCampo()" value="<%=p.getQuantidade() %>"><br/>
						<input type="text" name="preco" id="preco" onfocus="javascript:limpaCampo()" value="<%=p.getPreco() %>"><br/>
						<% out.print(p.buscaLocalEstoque()); %> <br/>
						<% out.print(p.buscaCategoriaProduto()); %> <br/>
		<br/><br/><br/>
					</div>	
		
		<br/><br/>
	
	<!-- modo antigo (simples) 
	<form action="gerenciaProduto.jsp" method="get">
		Código do Produto: <input type="text" name="codigo" id="codigo" onfocus="javascript:limpaCampo()" value="<%=p.getCodigo() %>"><br/>
		Descrição: <input type="text" name="descricao" value="<%=p.getDescricao() %>"><br/>
		Quantidade: <input type="text" name="qtde" id="qtde" onfocus="javascript:limpaCampo()" value="<%=p.getQuantidade() %>"><br/>
		Valor Unitário: <input type="text" name="preco" id="preco" onfocus="javascript:limpaCampo()" value="<%=p.getPreco() %>"><br/>
		Código do Local de Estoque: <% out.print(p.buscaLocalEstoque()); %> <br/>
		Código da Categoria do Produto: <% out.print(p.buscaCategoriaProduto()); %> <br/>
		<br/>
	
		<input type="submit" name="atualizar" value="Atualizar">
		<input type="submit" name="buscar" value="Buscar">
		<input type="submit" name="cadastrar" value="Cadastrar">
		<input type="submit" name="listar" value="Listar">
		<!--  <input type="submit" name="remover" value="Remover"> -->
		
		<input type="submit" name="atualizar" value="Atualizar">
		<input type="submit" name="buscar" value="Buscar" title="A busca deve ser realizada pela Descrição do produto">
		<input type="submit" name="cadastrar" value="Cadastrar">
		<input type="submit" name="listar" value="Listar">
		<input type="submit" name="voltar" value="Voltar">
		<input type="submit" name="relatorio" value="Baixo Estoque" title="Relatório dos produtos com baixa quantidade em estoque">
	</form>	
	
	</div>
	
		</div> <!-- FECHA INTERNO  -->
		<div class= 'base'></div>
	</div> <!-- FECHA FUNDO  -->
	
	<script type="text/javascript">limpaCampo2();</script>
	
</body>
</html>