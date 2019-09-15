<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="estilos.css" rel="stylesheet" type="text/css" />
</head>
<body>

	<%
		if(request.getParameter("Produto")!= null){
			response.sendRedirect("gerenciaProduto.jsp");
		}
		if(request.getParameter("NotaFiscal")!= null){
			response.sendRedirect("gerenciaNF.jsp");
		}
		if(request.getParameter("Fornecedor")!= null){
			response.sendRedirect("gerenciaFornecedor.jsp");
		}
		if(request.getParameter("CategoriadeProduto")!= null){
			response.sendRedirect("gerenciaCategoriaProduto.jsp");
		}
		if(request.getParameter("LocaldeEstoque")!= null){
			response.sendRedirect("gerenciaLocalEstoque.jsp");
		}
		if(request.getParameter("FornecedorProduto")!= null){
			response.sendRedirect("gerenciaFornecedorProduto.jsp");
		}
		if(request.getParameter("Sair")!= null){
			response.sendRedirect("login.jsp");
		}
		if(request.getParameter("Usuario")!= null){
			response.sendRedirect("gerenciaUsuario.jsp");
		}
		
		
	%>



<div class='fundo'>
		<div class= 'topo'></div>
			<div class= 'interno'>
				<div class= 'mensagem'> MENU </div>
				
				<div class= 'fundoBotao' style= 'height: 330px'>	
					<form action="menu.jsp" method="get">
				
						<div class= 'esquerda' style= 'height: 200px; text-align : center; line-height: 22px'>
						<br/>
					
						<input type="submit" name="NotaFiscal" value="Notas Fiscais"><br/><br/>
						<input type="submit" name="Produto" value="Produtos"><br/><br/>
						<input type="submit" name="Fornecedor" value="Fornecedores"><br/><br/>
						<input type="submit" name="Usuario" value="Usuários">
										
						</div>
						
						<div class= 'direita' style= 'height: 200px; text-align : center'>
						
						<input type="submit" name="CategoriadeProduto" value="Categorias dos Produtos" style= 'width : 170px'><br/><br/>
						<input type="submit" name="LocaldeEstoque" value="Locais de Estoque" style= 'width : 170px'><br/><br/>
						<input type="submit" name="FornecedorProduto" value="Controle de Compras" style= 'width : 170px'>
						
						
				
						</div>			
					
						<input type="submit" name="Sair" value="Sair" style= 'margin-left: 500px; margin-top: 88px'>
				</form>
			</div>	
		</div> <!-- FECHA INTERNO  -->
		<div class= 'base'></div>
</div> <!-- FECHA FUNDO  -->					

</body>
</html>