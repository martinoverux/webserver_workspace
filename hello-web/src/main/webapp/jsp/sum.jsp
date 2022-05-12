<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    

<%
	// jsp는 동적으로 컴파일되므로, tomcat 다시 시작할 필요가 없다.
	// scriptlet - 자바 영역	
	int sum = 0;
	for(int i = 1; i <= 100; i++){
		sum += i;
	}
	System.out.println("sum@server-side = " + sum);
	
	// long nowTime = new Date().getTime();
	long nowTime = System.currentTimeMillis(); // 현재 시스템 시각을 millis초로 반환
	System.out.println("time@server-side = " + nowTime);
	
	int n = 100;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp 기본</title>
</head>
<body>
	<h1>jsp 기본</h1>	
	
	<p id="server-side-sum"><%= sum %></p>
	<p id="client-side-sum"></p>
	<br />
	<p id="server-side-time"><%= nowTime %></p>
	<p id="client-side-time"></p>
	<br />
	<p id="server-to-client"></p>
	
	<script>
	const clientSideSum = document.querySelector("#client-side-sum");
	let sum = 0;
	for(let i = 1; i <= 100; i++){
		sum += i;
	}
	clientSideSum.innerHTML = sum;
	console.log("sum@client-side = ", sum);
	
	const clientSideTime = document.querySelector("#client-side-time");
	const now = new Date().getTime();
	clientSideTime.innerHTML = now;
	console.log("time@client-side = ", now);
	
	// 서버단 처리값을 자바스크립트에서 활용하기
	const k = 100 + <%= n %>;
	document.querySelector("#server-to-client").innerHTML = k;
	
	</script>
	
	<h2>주석</h2>
	<%-- jsp 주석 : servlet 변환과정에서 처리되지 않는다. 클라이언트에 전달되지 않는다. --%>
	<!-- html 주석 : client에 전달된다. -->
	
	<h2>분기처리</h2>
<%
	String type = request.getParameter("type");	
	System.out.println("type = " + type);

	if("abc".equals(type)){
%>
	<p>abcdefghijklmnopqrstuwxyz</p>
<%		
	} else if("가나다".equals(type)){
%>
	<p>가나다라마바사아자차카타파하</p>
<%		
	} else if("123".equals(type)){
%>
	<p>123456789</p>
<%		
	} else {
%>
	<p>타입이 지정되지 않았습니다. </p>
<%		
	}
%>

	<h2>반복처리</h2>
<%
	List<String> fruits = Arrays.asList("사과", "바나나", "아보카도", "키위");
%>	
	<ul>
<%
	for(String fruit : fruits){
		
%>
		<li><%= fruit %></li>
<%
	}
%>	
	</ul>
	
	<h2>@실습문제1</h2>
<%
	String type1 = request.getParameter("type");	
	if("abc".equals(type1)){
	String values1 = request.getParameter("value");
	String[] valueAbc = values1.split(",");
%>
	<ul>
<%
	for(String abc : valueAbc){
		
%>
		<li><%= abc %></li>
<%
	}
%>	
	</ul>
<%	
	}
%>		
	<h2>@실습문제2</h2>
<%
	String type2 = request.getParameter("type");	
	if("가나다".equals(type2)){
	String values2 = request.getParameter("value");
	String[] valueKor = values2.split(",");
%>
	<ul>
<%
	for(String korean : valueKor){
%>
		<li><%= korean %></li>
<%
	}
%>	
	</ul>
<%	
	}
%>	

<%--  강사님 풀이
    <h2>@실습문제</h2>
<% 
    if("abc".equals(type) || "가나다".equals(type)){
        String value = request.getParameter("value");
        
        if(value != null){
            String[] values = value.split(",");
%>
            <ul>
<%
            for(String val : values){
%>
                <li><%= val %></li>
<%                
            }
%>
            </ul>
<%            
        }    
    }
%>
 --%>
 
 
</body>
</html>