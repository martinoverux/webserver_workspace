<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.model.dto.Member"%>
<%
	Member member = (Member) session.getAttribute("member");
	String memberId = member.getMemberId();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<style>
div#delteMember-container{text-align:center; padding-top:50px;}
</style>
</head>
<body>
	<div id="delteMember-container">
		<p>[<%= memberId %>]님 정말로 탈퇴하시겠습니까?</p>
		<p><button type="button" onclick="deleteMember();">확인</button></p>
		<p><button type="button" onclick="closePopup();">닫기</button></p>
	</div>
<form name="deleteMemberFrm" method="POST" action="<%= request.getContextPath() %>/member/deleteMember">
	<input type="hidden" name="deleteMember" />
</form>
<script>
const deleteMember = () => { //post 방식으로 처리하기
	const frm = document.deleteMemberFrm;
	frm.submit();
}
const closePopup = () => {
			self.close(); 
};
		
</script>
</body>
</html>