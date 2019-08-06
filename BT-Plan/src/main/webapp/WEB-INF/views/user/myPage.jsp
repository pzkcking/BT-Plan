<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${  empty sid }">
	<script>
	window.alert('로그인해 주세요!');
	location.replace('index.BT');
	</script>
</c:if>
</head>
<script>
// 새로고침 금지
function noRefresh() {
	if (event.keyCode == 116) {
		alert("새로고침을 할 수 없습니다.");
		event.keyCode = 2;
		return false;
	} else if (event.ctrlKey && (event.keyCode == 78 || event.keyCode == 82)) {
		return false;
	}
}
document.onkeydown = noRefresh;
</script>
<style>
table {
	border: 1px;
	text-align: center;
}
fieldset {
	position: relative;
	text-align: center;
	margin: 0px auto;
}
</style>
<body>
<%@ include file="../header.jsp" %>
<h1 style="text-align: center; opacity: 0.8">MyPage</h1>
<hr>
<section style="margin: 0px auto;">
<fieldset style="width: 500px; align-content: center;" class="container-fluid">
<table border="1" style="margin-left: auto; margin-right: auto; border: 1px;
text-align: left; width: 500px" class="table table-striped table-hover">
<c:forEach var="dto" items="${ lists }">
	<tr>
		<th>회원 아이디:</th>
		<td><input type="text" readonly value="${ dto.uuserid }"></td>
	</tr>
	<tr>
		<th>회원 이름:</th>
		<td><input type="text" readonly value="${ dto.uname }"></td>
	</tr>
	<tr>
		<th>닉네임:</th>
		<td><input type="text" readonly value="${ dto.unickname }"></td>
	</tr>
	<tr>
		<th>생년월일:</th>
		<td><input type="text" readonly value="${ dto.ubirthday }"></td>
	</tr>
	<tr>
		<th>연락처</th>
		<td><input type="text" readonly value="${ dto.uphone }"></td>
	</tr>
	<tr>
		<th>E-Mail:</th>
		<td><input type="text" readonly value="${ dto.uemail }"></td>
	</tr>
	<tr>
		<th>주소:</th>
		<td><input type="text" readonly value="${ dto.uzipcode }"></td>
	</tr>
	<tr>
		<th></th>
		<td>
		<input type="text" readonly value="${ dto.uaddress1 }"><br>
		</td>
	<tr>
		<th></th>
		<td>
		<input type="text" readonly value="${ dto.uaddress2 }">
		<input type="text" readonly value="${ dto.uaddress3 }">
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button" class="btn btn-primary" value="회원정보 수정" onclick="location.href='userEditForm.BT'">
		<input type="button" class="btn btn-primary" value="이전 페이지" onclick="location.href='index.BT'">
		</td>
	</tr>
</c:forEach>
</table>
</fieldset>
</section>
<%@ include file="../footer.jsp" %>	
</body>
</html>