<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="dto" value="${result}" />
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
	<h1 style="text-align: center; opacity: 0.8">댓글 작성</h1>
<section style="margin: 0px auto;">
	<fieldset style="width: 900px; align-content: center;" class="container-fluid">
	<form name="textCommentForm" action="textCommentWrite.BT" align="center">
		<table style="margin-left: auto; margin-right: auto; border: 1px; text-align: left; width: 900px" class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<td>닉네임</td>
					<td>내용</td>
					<td>비고</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:set var="cc" value="${ccontentindex}"></c:set>
					<td><input type="text" name="cnickname" readonly value="${sid}">
					<input type="hidden" name="ccontentindex" value="${cc}"></td>
					<td><textarea rows="10" cols="100" name="ccomment"></textarea></td>
					<td><input type="submit" class="btn btn-primary" value="작성"></td>
				</tr>
			</tbody>
		</table>
	</form>
	</fieldset>
	</section>
	<%@ include file="../footer.jsp"%>
</body>
</html>