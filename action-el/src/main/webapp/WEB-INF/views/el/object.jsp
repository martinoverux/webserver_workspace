<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ServletRequest req = pageContext.getRequest();
	ServletResponse res = pageContext.getResponse();
	HttpSession sess = pageContext.getSession();
	ServletContext app = pageContext.getServletContext();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>El Objects</title>
</head>
<body>
	
	<h1>EL Objects</h1>
	<h2>param</h2>
	<ul>
		<li>${param.pname}</li>
		<li>${param.pcount}</li>
		<li>Option : 
			<ul>
				<li>${paramValues.option[0]}</li>
				<li>${paramValues.option[1]}</li>
			</ul>
		</li>
	</ul>
	
	<h2>header</h2>
	<ul>
		<li>${header.host}</li>
		<li>${header['user-agent']}</li>
		<li>${header['referer']}</li>
	</ul>
	
	<h2>cookie</h2>
		<ul>
			<li>${cookie.JSESSIONID.value}</li>
			<li>${cookie.asdfasd.value}</li>
		</ul>
		
	<h2>initParam</h2>
	<ul>
		<li>Tel : ${initParam.tel}</li>
		<li>Email : ${initParam.email}</li>
	</ul>
	
	<h2>pageContext</h2>
	<ul>
		<li><%= request.getMethod() %></li>
		<li>${pageContext.request.method}</li>
		<li><%= request.getContextPath() %></li>
		<li>${pageContext.request.contextPath}</li>
	</ul>
	
	
</body>
</html>