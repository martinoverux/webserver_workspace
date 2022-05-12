<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp" %>

		<h1>main1</h1>
		<!-- p>lorem -->
		<p><%= name %>님, 반갑습니다.</p>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloribus labore hic cum alias vel voluptatem facere quia repellendus ullam voluptatum iusto ipsam fuga a debitis nam delectus architecto iste necessitatibus.</p>
		
		
		<script>
		const title = document.querySelector("header h1").innerHTML;
		alert(title);
		</script>
<%@ include file="/jsp/footer.jsp" %>
	
