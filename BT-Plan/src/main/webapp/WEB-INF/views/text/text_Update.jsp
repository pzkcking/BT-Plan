<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var = "dto" value = "${result}"/>
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
	
		<fieldset style="width: 700px; align-content: center;" class="container-fluid">
	<article>
		<h2>게시판 본문</h2>
		<form name = "content" action = "textUpdate_ok.BT">
			<table style="margin-left: auto; margin-right: auto; border: 1px;
text-align: left; width: 600px" class="table table-striped table-hover table-bordered">
				<tr>
					<th>번호</th>
					<td><input type = "text" name = "tindex" value = "${dto.tindex}"></td>
					<th>작성날짜</th>
					<td><input type = "text" name = "Uppostdate" value = "${dto.tpostdate}"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type = "text" name = "tnickname" value = "${dto.tnickname}" readonly = "readonly"></td>
					<th>조회수</th>
					<td><input type = "text" name = "tviewcount" value = "${dto.tviewcount}" readonly = "readonly"></td>
					
				</tr>
				<tr> 
					<th>제목</th>
					<td colspan = "1"><input type = "text" name = "ttitle" value = "${dto.ttitle}"></td>
					<td>카테고리</td>
						<td>
							<select name = "tcategory">
								<option value = "1" selected>${dto.tcategory}</option>
								<option value = "2">여행</option>
								<option value = "3">여가</option>
								<option value = "4">휴가</option>
								<option value = "5">출장</option>
							</select>
						</td>
				</tr>
				<tr>
					<td colspan = "4"><textarea rows="10" cols="85" name ="tcontent" style="resize: none;" >${dto.tcontent}</textarea></td>
				</tr>
				<tr>
					<td colspan = "4" align = "center">
					<input type = "submit" class="btn btn-primary" value = "수정완료">
					<a href = "textList.BT">목록보기</a>
					</td>
				</tr>
			</table>
		</form>
	</article>
	</fieldset>
</section>
<%@ include file="../footer.jsp"%>
</body>
</html>