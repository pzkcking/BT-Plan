<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<c:set var="arr" value="${textcomment}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
fieldset {
	position: relative;
	text-align: center;
	margin: 0px auto;
}
</style>
<body>
<%@ include file="../header.jsp"%>
	
<section style="margin: 0px auto;">

	<fieldset style="width: 900px; align-content: center;" class="container-fluid">
		<legend>댓글</legend>
		<table style="margin-left: auto; margin-right: auto; border: 1px; text-align: left; width: 500px" class="table table-striped table-hover table-bordered">
			<thead>
			<tr>
				<td>댓글번호</td>
				<td>닉네임</td>
				<td>내용</td>
				<td>작성날짜</td>
			</tr>
			</thead>
			<tbody>
					<c:if test="${empty arr}">
						<tr>
							<td colspan = "4" style="text-align: center;">댓글이 없습니다<td>
						</tr>
					</c:if>
			<c:forEach var="list" items="${arr}">		
						<tr>
							<td>${list.cindex}</td>
							<td>${list.cnickname}</td>
							<td><textarea rows="3" cols="30" readonly = "readonly">${list.ccomment}</textarea></td>
							<td>${list.cpostdate}</td>
							<c:set var = "dto" value = "${result}"/>
							<c:choose>
								<c:when test="${list.cnickname eq tnickname}">
								<td>
									<a href ="textCommentDelete.BT?cindex=${list.cindex}">
									<input type = "button" class="btn btn-primary" value = "삭제"><br></a>
								</td>
								</c:when>
							</c:choose>		
						</tr>
			</c:forEach>			
			</tbody>
			
		</table>
	</fieldset>

</section>
<%@ include file="../footer.jsp"%>
</body>
</html>