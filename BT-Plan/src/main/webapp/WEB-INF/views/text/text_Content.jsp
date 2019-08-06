<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "dto" value = "${result}"/>
<c:set var = "comment" value = "${textcomment}"/>
<c:set var = "commentt" value = "${textcommentStr}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function show()
{
	history.back();	
}
</script>
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
	<article>
		<fieldset style="width: 700px; align-content: center;" class="container-fluid">
		<h2>게시판 본문</h2>
		<form name = "content">
			<table style="margin-left: auto; margin-right: auto; border: 1px;
text-align: left; width: 500px" class="table table-striped table-hover table-bordered">
				<tr>
					<th>번호</th>
					<td>${dto.tindex}</td>
					<th>작성날짜</th>
					<td>${dto.tpostdate}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${dto.tnickname}</td>
					<th>조회수</th>
					<td>${dto.tviewcount}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan = "3">${dto.ttitle}</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<c:choose>
						<c:when test="${dto.tcategory == 1}">
							<td>선택</td>
						</c:when>
						<c:when test="${dto.tcategory == 2}">
							<td>여행</td>
						</c:when>
						<c:when test="${dto.tcategory == 3}">
							<td>여가</td>
						</c:when>
						<c:when test="${dto.tcategory == 4}">
							<td>휴가</td>
						</c:when>
						<c:otherwise>
							<td>출장</td>
						</c:otherwise>
					</c:choose>
					<th>추천수</th>
					<td>${dto.tlikecount}</td>
				</tr>
				<tr>
					<td colspan = "4"><textarea rows="10" cols="65" name ="tcontent" readonly="readonly">${dto.tcontent}</textarea></td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${dto.tnickname eq sid}">
							<td style="text-align: right;" colspan="4"><a href = "textDelete.BT?tindex=${dto.tindex}">
							<input type = "button" class="btn btn-primary" value = "삭제하기"></a>
							<a href = "textUpdate.BT?tindex=${dto.tindex}">
							<input type = "button" class="btn btn-primary" value = "수정하기"></a></td>
						</c:when>
						
					</c:choose>
				</tr>
				<tr>
					<td colspan = "4" align = "center">
					<input type = "button" class="btn btn-primary" onclick = "show()" value = "목록으로">
					<a href = "textLikeCount.BT?tindex=${dto.tindex}&cp=">추천</a>
					<a href = "textCommentWriteForm.BT?ccontentindex=${dto.tindex}">댓글 작성하기</a>
					<a href = "textCommentList.BT?ccontentindex=${dto.tindex}">댓글 보기</a>
					</td>
				</tr>
			</table>
		</form>
		</fieldset>
	</article>
</section>
<%@ include file="../footer.jsp"%>
</body>
</html>