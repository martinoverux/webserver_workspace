<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jqury - text</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

</head>
<body>
	<h1>text</h1>
	<button id="btn1">text</button>
	<script>
	btn1.addEventListener('click', (e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/text", 
			method : "GET", // 전송방식 GET(기본값) | POST | PUT | PATCH
			data : {
				q : "abcde",
				mode : 123,
				isFinal : true
			}, // 사용자 입력값 직렬화 처리후 GET 방식이명 url에 추가, POST 방식이면 body 부분에 작성
			dateType : "text", // text | html | script | json | xml
			beforeSend(){
				// 요청 전 호출 메소드
				console.log("beforeSend");
			},
			success(responseText){
				// xhr.responseText 후처리 후 success 메소드에 전달!
				// readyState == 4 && status == 200
				console.log("success : ", responseText);
			},
			error(xhr, textStatus, err) {
				// readyState == 4 && status != 200
				console.log("error : ", xhr, textStatus, err);
			},
			complete(){
				// 응답 후(성공, 실패) 반드시 실행하는 메소드
				console.log("complete")
			}
		});
	});
	</script>
	
	<button id="btn2">csv</button>
	<table id="tbl-celeb">
		<thead>
			<tr>
				<th>No</th>
				<th>이름</th>
				<th>타입</th> <!-- select 태그 하위에 해당 타입이 selected처리 -->
				<th>프로필</th> <!-- img 태그 처리 -->
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>
	<div id="result"></div>
	<script>
	
	/**
	* csv comma seperated value
	*
	*/
	btn2.addEventListener('click', (e) => {
		$.ajax({
			url : "<%= request.getContextPath() %>/jquery/csv",
			method : "GET",
			dataType : "text",
			success(response){
				console.log(response);
				document.querySelector("#tbl-celeb tbody").innerHTML = response;
			},
			error(xhr, textStatus, err){
				console.log("error : ", xhr, textStatus, err);
			}
			
		});		
	});
	</script>
</body>
</html>