<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="css/bootstrap.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>

	<section style="margin: 0px auto;">
	<article>
			<fieldset class="container-fluid">
				<legend>쪽지함</legend>
						<form name="message" action="messageRead.BT">
					<table border="1" class="table table-striped table-hover">
						<c:set var="arr" value="${lists }"></c:set>
						<c:forEach var="dto" items="${arr }">		
						<tr>
							<td>보낸 사람</td>
							<td>${dto.msender }</td>		
							<td>제목</td>
							<td>${dto.msubject }</td>	
							<td>보낸 날짜</td>
							<td>${dto.mwritedate }</td>	
						</tr>
						<tr>
							<td>내용</td>
							<td colspan="10"><textarea rows="10" cols="100" readonly="readonly" style="resize: none;">${dto.mcontent }</textarea>	
						<tr>		
							</c:forEach>		
					</table>
				
				</form>
				
			</fieldset>
		</article>
		</section>

</body>
</html>