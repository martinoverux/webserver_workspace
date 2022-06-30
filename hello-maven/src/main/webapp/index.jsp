<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="utf-8"/>
	<script src="<c:url value="/js/jquery-3.6.0.js"/>"></script>
</head>
<body>
	<h2>Hello World!</h2>
	<button id="btn">확인</button>
	
	<script>
	document.querySelector("#btn").addEventListener('click', (e) => {
		$.ajax({
			url : "${pageContext.request.contextPath}/helloworld",
			success(data){
				console.log(data);
				const {msg} = data;
				alert(msg);
			},
			error : console.log
		});		
	});
	</script>
</body>
</html>
