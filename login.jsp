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
	if(request.getParameter("acessar") != null){
		String matricula = request.getParameter("matricula");
		String senha = request.getParameter("senha");
		projetofinal.Usuario u = new projetofinal.Usuario();
		
		if (u.executaLogin(matricula, senha) == true) {
			out.print("<script>alert('Bem-vindo!')</script>");
			response.sendRedirect("menu.jsp");
		}else{
			out.print("<script>alert('Usuário ou senha incorreta!')</script>");
		}
		
	}
	%>
	
	<div class='fundo'>
		<div class= 'topo'></div>
			<div class= 'interno'>
				<div class= 'mensagem'> ACESSO AO SISTEMA</div>
				
				<div class= 'fundoBotao'>
				<form action="login.jsp" method="get">
				
				
					<div class= 'esquerda' style='height: 120px'> 
						<span>Matrícula:</span><br/>
						<span>Senha:</span><br/>
					</div>
					
					<div class= 'direita' style='height: 120px'>
	
						<input type="text" name="matricula">
							<br/>
						<input type="password" name="senha">
							<br/>
		<br/><br/><br/>
					</div>	
					<br/><br/>
					
					<input type="submit" name="acessar" value="Acessar">
					</form>	
				
				</div>
	
		</div> <!-- FECHA INTERNO  -->
		<div class= 'base'></div>
	</div> <!-- FECHA FUNDO  -->
</body>
</html>