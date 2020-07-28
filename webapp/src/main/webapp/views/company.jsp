<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test company</title>
</head>
<body>
	Computer :
	<c:forEach items="${computerList}" var="computer">
		<tr>
			<td>${computer.id}</td>
			<td>${computer.name}</td>
			<td>${computer.introduced}</td>
			<td>${computer.discontinued}</td>
			<td>${computer.company.name}</td>
		</tr>
	</c:forEach>
</body>
</html>