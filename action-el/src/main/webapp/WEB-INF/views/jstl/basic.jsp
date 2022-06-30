<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Basics</title>
</head>
<body>
	<h1>JSTL Basics</h1>
	<h2>두수의 합구하기</h2>
	<form name="addFrm" method="POST">
		<label for="num1">첫번째수 : </label>
		<input type="number" name="num1" id="num1" />
		<br />
		<label for="num2">두번째수 : </label>
		<input type="number" name="num2" id="num2" />
		<br />
		<button>제출</button>
	</form>
	
	<h2>경품뽑기</h2>
	<form name="rndFrm" method="POST">
		<input type="hidden" name="rnum" />
		<input type="hidden" name="login" value="false" />
		<button>뽑기</button>
	</form>
	<script>
	document.rndFrm.onsubmit = (e) => {
		e.target.rnum.value = Math.floor(Math.random() * 100) + 1;
	}
	</script>
	
</body>
</html>