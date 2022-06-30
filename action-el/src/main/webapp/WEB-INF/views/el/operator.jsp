<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Collections"%>
<%@page import="com.kh.person.model.dto.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String str1 = "안녕";
	String str2 = new String("안녕");
	
	int x = 100;
	int y = 30;
	
	Person honggd1 = new Person("홍길동", 'M', 33, false);
	Person honggd2 = new Person("홍길동", 'M', 33, false);
	
	List<String> list1 = null;
	List<String> list2 = Collections.emptyList();
	List<String> list3 = Arrays.asList("123", "456", "789");
	
	pageContext.setAttribute("str1", str1);
	pageContext.setAttribute("str2", str2);
	pageContext.setAttribute("x", x);
	pageContext.setAttribute("y", y);
	pageContext.setAttribute("honggd1", honggd1);
	pageContext.setAttribute("honggd2", honggd2);
	pageContext.setAttribute("list1", list1);
	pageContext.setAttribute("list2", list2);
	pageContext.setAttribute("list3", list3);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Operator</title>
</head>
<body>
	<h1>EL Operator</h1>
	<%-- 
		단순한 출력 용도의 EL이지만, 산술연산/논리연산 지원.
	 --%>
	 <h2>산술연산</h2>
	 <ul>
	 	<li>${x + y}</li>
	 	<li>${list3[0] + x}</li>
	 	<%-- <li>${str1 + str2}</li> --%> <%-- NumberFormatException 유발 --%>
	 	<li>${str1}${str2}</li>
	 	<li>${x - y}</li>
	 	<li>${x * y}</li>
	 	<li>${x / y} ${x div y}</li> <%-- 3.3333333333333335 --%>
	 	<li>${x % y} ${x mod y}</li>
	 </ul>
	 
	 <h2>비교연산</h2>
	 <ul>
	 	<li>${x > y} ${x gt y}</li>
	 	<li>${x >= y} ${x ge y}</li> <%-- greater than or equal to --%>
	 	<li>${x < y} ${x lt y}</li>
	 	<li>${x <= y} ${x le y}</li>
	 	<li>${x == y} ${x eq y}</li>
	 	<li>${x != y} ${x ne y}</li>
	 </ul>
	
	<h2>동등비교연산</h2>
	<ul>
		<li>String : <%= str1 == str2 %> ${str1 == str2} ${str1 eq str2}</li> <%-- el안의 ==, eq 모두 내부적으로 equals 호출해 처리 --%>
		<li>Person : ${honggd1 eq honggd2}</li>
	</ul>
	
	<h2>empty</h2>
	<!-- 배열, 컬렉션, 일반객체에 대해서 null여부, 요소존재여부(배열/컬렉션) 검사 -->
	<ul>
		<li>${empty list1} </li> <%-- true --%>
		<li>${empty list2}</li> <%-- true --%>
		<li>${empty list3}</li> <%-- false --%>
		<li>${empty ""}</li> <%-- true --%>
		<li>${!empty list1} ${not empty list1}</li> <%-- 반전 --%>
	</ul>
	
	<h2>논리연산자</h2>
	<ul>
		<li>${true && true} ${true and true}</li>
		<li>${true || false} ${true or false}</li>
	</ul>
	
	
	


	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
</body>
</html>