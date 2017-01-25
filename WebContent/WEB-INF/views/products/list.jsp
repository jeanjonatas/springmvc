<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>Titulo</td>
			<td>Valores</td>
		</tr>
		<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.title}</td>
			<td><c:forEach items="${product.prices}" var="price">
				     	[${price.value}	--	${price.bookType}]
				     </c:forEach>
	
			</td>
		</tr>
		</c:forEach>
	</table>
	<h1>${sucesso}</h1>
</body>
</html>