<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="js/httpRequest.js"></script>
<script src="js/jquery-3.4.1.js"></script>
<script type="text/javascript"></script>

<script>
	function OnCalendarLoginEvent() {
		if (document.calendarLoginForm.idRootName.value == ''
				|| document.calendarLoginForm.idCodeName.value == '') {
			event.returnValue = false;
			window.alert('ID or Password를 입력해주세요.!')
		}
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
	<br>
	<br>
	<br>
	<br>
	<h1 style="text-align: center; opacity: 0.8">캘린더 로그인</h1>
	<section style="margin: 0px auto;">
		<fieldset style="width: 900px; align-content: center;"
			class="container-fluid">
			<form name="calendarLoginForm" action="calendarLoginOK.BT"
				method="post" align="center">
				RootName:<input id="idRootName" type="text" name="wrootname"><br>
				<br> CodeName:<input id="idCodeName" type="password"
					name="wcodename"><br> <input type="submit"
					class="btn btn-primary" class="btn btn-primary" value="확인"
					onclick="OnCalendarLoginEvent()"><br>
			</form>
		</fieldset>
	</section>
	<br>
	<br>
	<br>
	<br>
	<%@ include file="../footer.jsp"%>
</body>
</html>