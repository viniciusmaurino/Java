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
		document.getElementById("matricula").value = "";
		document.getElementById("senha").value = "";
		document.getElementById("senhaa").value = "";
	}
</script>
</head>
<body>
	<%!
		//Declaração de variável global
		projetofinal.Usuario u = new projetofinal.Usuario();
	%>
	
	<%
	//Testando qual botão foi clicado
	if (request.getParameter("voltar") != null) {
		response.sendRedirect("menu.jsp");
	}
	
	
		if(request.getParameter("cadastrar") != null){
			String matricula = request.getParameter("matricula"); // pegando valores dos campos do formulário html
			String senha = request.getParameter("senha");
			String nivel = request.getParameter("nivel");
			
			u = new projetofinal.Usuario(matricula, senha, nivel);
			if(u.cadastraUsuario(matricula, senha, nivel) == true){
				out.print("<script>alert('Usuário cadastrado com sucesso!')</script>");
			}
		}
	
		/*
		if(request.getParameter("buscar") != null){
			String matricula = request.getParameter("matricula");
			
			out.print(u.buscaUsuario(matricula));
		}
		*/
		
		if(request.getParameter("remover") != null){
			String matricula = request.getParameter("matricula");
			
			if(u.removeUsuario(matricula) == true){
				out.print("<script>alert('Usuário removido com sucesso!')</script>");
			}
		}
		
		if(request.getParameter("atualizar") != null){
			String matricula = request.getParameter("matricula");
			String senha = request.getParameter("senha");
			String nivel = request.getParameter("nivel");
			String senhaa = request.getParameter("senhaa");
	
			if(u.atualizarUsuario(matricula, senha, nivel, senhaa) == true){
				out.print("<script>alert('Dados alterados com sucesso!')</script>");
			}
		}
		
		/*
		if(request.getParameter("listar") != null) {
			out.print(u.exibeUsuario());
		}
		*/
	%>
	
	<div class='fundo'>
		<div class= 'topo'></div>
			<div class= 'interno'>
				<div class= 'mensagem'> USUÁRIOS</div>
				
				<% 
				if(request.getParameter("buscar") != null){
					String matricula = request.getParameter("matricula");
					
					out.print(u.buscaUsuario(matricula));
				}
				if(request.getParameter("listar") != null) {
					out.print(u.exibeUsuario());
				}
				%>
				
				<div class= 'fundoBotao'>
				<form action="gerenciaUsuario.jsp" method="get">
				
				
					<div class= 'esquerdaMenor' style='height: 180px'> 
						<span>Matrícula:</span><br/>
						<span>Senha:</span><br/>
						<span>Tipo de Usuário:</span><br/>
						<span>Senha Antiga:</span>
						
					</div>
						
					<div class= 'direita' style='height: 180px'>
	
						<input type="text" name="matricula" id="matricula" value="<%=u.getMatricula() %>">
							<br/>
						<input type="password" name="senha" id="senha" value="<%=u.getSenha() %>">
							<br/>
						<!--  Tipo de Usuário: <input type="text" name="nivel" value="<%=u.getNivelAcesso() %>">  -->
						<select name="nivel">
							<option value="USUARIO">USUARIO</option>
							<option value="ADMINISTRADOR">ADMINISTRADOR</option>
						</select>
							<br/>
						<input type="password" name="senhaa" id="senhaa" value="<%=u.getSenha() %>">
							<br/>
		<br/><br/><br/>
					</div>	
					<br/><br/>
					
						<input type="submit" name="atualizar" value="Atualizar">
						<input type="submit" name="buscar" value="Buscar" title="A busca deve ser realizada pela Matrícula do usuário">
						<input type="submit" name="cadastrar" value="Cadastrar">
						<input type="submit" name="listar" value="Listar">
						<input type="submit" name="remover" value="Remover">
						<input type="submit" name="voltar" value="Voltar">
					
					</form>	
				
				</div>
	
		</div> <!-- FECHA INTERNO  -->
		<div class= 'base'></div>
	</div> <!-- FECHA FUNDO  -->
	
	<script type="text/javascript">limpaCampo2();</script>
	
</body>
</html>