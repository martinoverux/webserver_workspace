<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="board.model.dto.BoardExt"%>
<%@page import="board.model.dto.Attachment"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<BoardExt> boardList = (List<BoardExt>) request.getAttribute("boardList");
	List<Attachment> attachList = (List<Attachment>) request.getAttribute("attachList");
	String pagebar = (String) request.getAttribute("pagebar");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<section id="board-container">
	<h2>게시판 </h2>
<% if (loginMember != null) { %>
	<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/board/boardEnroll';"/>
<% } %>
	<table id="tbl-board">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th><%--첨부파일이 있는 경우 /images/file.png 표시 width:16px --%>
				<th>조회수</th>
			</tr>		
		</thead>
		<tbody>
<%
		if(boardList != null && !boardList.isEmpty()){
			for(BoardExt board : boardList){
%>	
			<tr>
				<td><%= board.getNo() %></td>
				<td><%= board.getTitle() %></td>
				<td><%= board.getMemberId()%></td>
				<td><%= board.getRegDate()%></td>
				<td>
					<% if(board.getAttachCount() > 0) { %>					
								<img src="<%= request.getContextPath() %>/images/file.png" alt="" />
					<% } %>					
				</td>			
				<td><%= board.getReadCount()%></td>
			</tr>
<% } 
}%>
		</tbody>
	</table>
	<div id="pagebar">
		<%= pagebar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
