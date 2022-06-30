<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	id 속성에 저장된 key값
	class 패키지포함 풀네임
	scope page(기본값) | request | session | application
 --%>
<jsp:useBean id="person" class="com.kh.person.model.dto.Person" scope="request"></jsp:useBean>
<jsp:useBean id="sinsa"  class="com.kh.person.model.dto.Person"/>
<jsp:setProperty property="name" value="신사임당" name="sinsa"/>
<jsp:setProperty property="gender" value="여" name="sinsa"/>
<jsp:setProperty property="age" value="40" name="sinsa"/>
<jsp:setProperty property="married" value="false" name="sinsa"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBean</title>
</head>
<body>
	<h1>useBean</h1>
	<h2>홍길동</h2>
	<ul>
		<%-- property는 getter의 get을 제외하고, 소문자로 시작하는 이름 작성 --%>
		<%-- OGNL스펙 (Object Graph Navigation Language)에 따로 getter/setter이름을 참조 --%>
		<li>이름 : <jsp:getProperty property="name" name="person"/></li>
		<li>성별 : <jsp:getProperty property="gender" name="person"/></li>
		<li>나이 : <jsp:getProperty property="age" name="person"/></li>
		<li>결혼여부 : <jsp:getProperty property="married" name="person"/></li>
	</ul>
	
	<h2>신사임당</h2>
	<ul>
		<li>이름 : <jsp:getProperty property="name" name="sinsa"/></li>
		<li>성별 : <jsp:getProperty property="gender" name="sinsa"/></li>
		<li>나이 : <jsp:getProperty property="age" name="sinsa"/></li>
		<li>결혼여부 : <jsp:getProperty property="married" name="sinsa"/></li>
	</ul>
		
</body>
</html>