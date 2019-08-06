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
<h1 style="text-align: center; opacity: 0.8">검색 결과</h1>
<section style="margin: 0px auto;">
	<fieldset style="width: 700px; align-content: center;" class="container-fluid">
<form name = "fm1">
<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 500px" class="table table-striped table-hover table-bordered">
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
	
	<tbody>
	<c:choose>
		<c:when test="${empty lista}">
			<tr>
				<td>정렬된 게시물이 없습니다</td>
			</tr>
		</c:when>
	</c:choose>
	<c:forEach var="list" items="${lista}">
		<tr>
			<td>${list.tindex}</td>
			<td><a href = "textContent.BT?tindex=${list.tindex}">${list.ttitle}</a></td>
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
	</tbody>
	<tfoot>
			<tr>
				<td>
				<div style="text-align: center;">${requestScope.pageStr1}</div>
				</td>
			</tr>
	</tfoot>
</table>
</form>
<hr>
<form name="textsearch" action="textcategoryList.BT">
		<select name="tag">
			<option value = "1">카테고리</option>
			<option value = "2">여행</option>
			<option value = "3">여가</option>
			<option value = "4">휴가</option>
			<option value = "5">출장</option>
		</select> 
		<input type="hidden" name ="cp" value = "1">
		<input class="btn btn-primary" type = "submit" value = "검색">
	</form>
</fieldset>
</section>
</body>
</html>