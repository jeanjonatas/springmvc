<hmtl>
<body>
	<%--pagina jsp, comentarios dessa forma, jsp serve para misturar paginas html e código java --%>
	<%
		String mensagem = "Bem vindos";
	%>
	<%
		out.println(mensagem);
	%>
	<br />
	<%
		String desenvolvimento = "Desenvolvido por Jean";
	%>
	<%=desenvolvimento%>

	<br />

	<%
		System.out.println("Tudo foi executado");
	%>
</body>
</html>