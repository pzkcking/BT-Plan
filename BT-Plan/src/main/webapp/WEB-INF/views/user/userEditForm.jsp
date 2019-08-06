<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:if test="${ empty sid }">
	<script>
	window.alert('로그인해 주세요!');
	location.replace('index.BT');
	</script>
</c:if>
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
fieldset {
	position: relative;
	text-align: center;
	margin: 0px auto;
}
</style>
</head>
<body>
<%@ include file="../header.jsp" %>
<section style="margin: 0px auto;">
<fieldset style="width: 500px; align-content: center;" class="container-fluid">
<legend>내 정보 수정</legend>
<div style="text-align: center; font-size: 15px;">마이페이지의 정보를 수정하기 위한 페이지 입니다. <br>비밀번호를 다시한번 입력해주셔야 가능합니다.</div><br>
<form name="userEdit" action="userEdit.BT" method="post">		
<div style="text-align: center">
비밀번호 : &nbsp;<input type="password"  name="Upassword" placeholder="비밀번호" required="required">
<input type="hidden" name="Uuserid" value="${ Uuserid }"></div><br>
<input type="submit" class="btn btn-primary" value="수정하기">&nbsp;
<input type="button" class="btn btn-primary" value="이전 페이지" onclick="location.href='index2.BT'"><br>
</form>
</fieldset>
</section>
<br>
<br>
<br>
<%@ include file="../footer.jsp" %>
</body>
</html>