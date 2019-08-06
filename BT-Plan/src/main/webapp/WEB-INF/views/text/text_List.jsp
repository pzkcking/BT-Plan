<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<h1 style="text-align: center; opacity: 0.8">자유게시판</h1>
<section style="margin: 0px auto;">
	<fieldset style="width: 800px; align-content: center;" class="container-fluid">
	<form name="fm">
		<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 800px" class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>조회수</th>
					<th>작성날짜</th>
					<th>카테고리</th>
				</tr>
			</thead>
			<c:set var="arr" value="${lists }"></c:set>
			<c:choose>
				<c:when test="${empty lists}">
					<tr>
						<td>등록된 게시물이 없습니다</td>
					</tr>
				</c:when>
			</c:choose>
			<tbody>
				<c:forEach var="list" items="${arr}">
		<tr>
			<td>${list.tindex}</td>
			<td>
			<a href = "textContent.BT?tindex=${list.tindex}">${list.ttitle}</a>
			(
			<a href = "textCommentList.BT?ccontentindex=${list.tindex}">댓글 보기</a>
			)
			</td>
			<td>${list.tnickname}</td>
			<td>${list.tviewcount}</td>
			<td>${list.tpostdate}</td>
						<c:choose>
							<c:when test="${list.tcategory == 1}">
								<td>선택</td>
							</c:when>
							<c:when test="${list.tcategory == 2}">
								<td>여행</td>
							</c:when>
							<c:when test="${list.tcategory == 3}">
								<td>여가</td>
							</c:when>
							<c:when test="${list.tcategory == 4}">
								<td>휴가</td>
							</c:when>
							<c:otherwise>
								<td>출장</td>
							</c:otherwise>
						</c:choose>
			</tr>
		</c:forEach>
				<tr>
					<td colspan="6" style="text-align: right;"><a href="text.BT">
					<input class="btn btn-primary" type="button" value="글쓰기"></a></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6"">
						<div style="text-align: center;">${requestScope.pageStr}</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
	</fieldset>
	</section>
	<%@ include file="../footer.jsp"%>
</body>
</html>