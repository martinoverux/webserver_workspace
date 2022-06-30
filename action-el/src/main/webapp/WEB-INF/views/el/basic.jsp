<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	Expression Language 
	
	jsp 2.0부터 추가된 스펙으로 <%= %>를 더 간결히 작성하기 위한 문법.
	
	${value}
	- 각 스코프별 내장객체(map) pageScope, requestScope, sessionScope, applicationScope
	- param, paramValues(map) 사용자입력값
	- header, headerValues(map) 요청헤더
	- cookie(map)
	- initParam(map) 초기화파라미터
	- pageContext(실제참조)
 --%>
 <%
 	pageContext.setAttribute("book", "협상의 기술");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Basic</title>
</head>
<body>
	<h1>EL Basic</h1>
	<h2>request</h2>
	<ul>
		<li>${requestScope.coffee}</li>
		<li>${requestScope.num}</li>
		<li>${requestScope.honggd}
			<ul>
				<li>${requestScope.honggd.name}</li>
				<li>${requestScope.honggd.gender}</li>
				<li>${requestScope.honggd.age}</li>
				<li>${requestScope.honggd.married}</li>
			</ul>
		</li>
		<li>${items}
			<ul>
				<li>${items[0]}</li>
				<li>${items[1]}</li>
				<li>${items[2]}</li>
				<li>${items[3]}</li>
				<li>${items[4]}</li> <%-- 존재하지 않는 인덱스 참조시 "" 반환 --%>
			</ul>
		</li>
		<li>${map}
			<ul>  
				<li>${map.name}</li>
				<li>${map.today}</li>
				<%-- 
					bracket notion 사용가능
						- 특수문자 공백이 포함된 key값처리에 유용 
						- key값처리에 쌍따옴표, 홑따옴표 모두 가능
				--%>
				<li>${map["Dr.Zang"]}
					<ul>
						<li>${map["Dr.Zang"].name}</li>
						<li>${map["Dr.Zang"].gender}</li>
						<li>${map["Dr.Zang"].age}</li>
						<li>${map["Dr.Zang"].married}</li>
					</ul>
				</li> 
				<li>${map["Dr.Zang"]}
					<ul>
						<li>${map["Dr.Zang"]['name']}</li>
						<li>${map["Dr.Zang"]['gender']}</li>
						<li>${map["Dr.Zang"]['age']}</li>
						<li>${map["Dr.Zang"]['married']}</li>
						<li><input type="checkbox" ${map["Dr.Zang"]['married']} ? "checked : "" } /></li>
					</ul>
				</li> 
			</ul>
		</li>
	</ul>
	
	<h2>session</h2>
	<ul>
		<li>${book}</li> <%-- pageScope -> requestScope -> sessionScope -> applicationScope 순으로 조회 --%>
		<li>${sessionScope.book}</li>
		<li>${xyz}</li> <%-- el은 찾는 값이 없을때 null이 아닌 ""을 반환한다. --%>
		<li>${x.yz}</li> <%-- el은 NPE을 던지지 않는다. --%>
	</ul>
	
	<h2>application</h2>
	<ul>
		<li>${movie}</li>
	</ul>

</body>
</html>










