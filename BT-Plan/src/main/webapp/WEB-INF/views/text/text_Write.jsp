<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
String REMOTE_ADDR = request.getRemoteAddr();
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
ody>
	<%@ include file="../header.jsp"%>
	<h1 style="text-align: center; opacity: 0.8">자유게시판</h1>
<section style="margin: 0px auto;">
	<fieldset style="width: 700px; align-content: center;" class="container-fluid">
<form name = "textWrite" action = "textWrite.BT">
	<table style="margin-left: auto; margin-right: auto; border: 1px;
				text-align: left; width: 500px" class="table table-striped table-hover table-bordered">
		<tr>
			<th>작성자</th>
			<td><input type = "text" name = "tnickname" readonly ="readonly" value = "${sid}"></td>
		</tr>
		<tr>
		<th>카테고리</th>
			<td>
				<select name = "tcategory">
						<option value="1">카테고리</option>
						<option value="2">여행</option>
						<option value="3">여가</option>
						<option value="4">휴가</option>
						<option value="5">출장</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan = "4"><input type = "text" name = "ttitle"></td>
		</tr>
		<tr>
			<td colspan = "4"><textarea rows="10" cols="65" name ="tcontent"></textarea></td>
		</tr>
		<tr>
			<td colspan = "4"><input type = "hidden" name = "tipaddress">${REMOTE_ADDR}</td>
		</tr>
		<tr>
			<th colspan = "4" style="text-align: right;">
				<input type = "reset" class="btn btn-primary" value = "다시쓰기">
				<input type = "submit" class="btn btn-primary" value = "글쓰기">
			</th>
		</tr>
	</table>
</form>
</fieldset>
</section>
<%@ include file="../footer.jsp"%>
</body>
</html>