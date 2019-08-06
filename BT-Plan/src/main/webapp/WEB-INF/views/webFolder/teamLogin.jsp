<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
window.onload = function() {
	loginShow();
}
</script>
<script>
function loginShow() {
	$("#login").show();
	$("#rootCreate").hide();
	$("#findCode").hide();
}
function rootCreateShow() {
	$("#login").hide();
	$("#rootCreate").show();
	$("#findCode").hide();
}
function findCodeShow() {
	$("#login").hide();
	$("#rootCreate").hide();
	$("#findCode").show();
}
function rootCheck() {
	var id = document.getElementById("rootName").value;
	var pattern = /[!@#$%^&*()+=/\?/'<">:,;.~`]/;
	
	if (id.value != '') {
		if (pattern.test(id) || id.length < 5) {
			alert("아이디는 5자이상 12자 이하만 가능합니다. 특수문자는 _만 사용가능합니다.");
			document.getElementById('rootCheck').innerHTML = '잘못된 유형입니다.';
			document.getElementById('rootCheck').style.color = 'red';
			document.getElementById('rootName').value = "";
			return false;
		} else {
			document.getElementById('rootCheck').innerHTML = '사용가능한 유형입니다.';
			document.getElementById('rootCheck').style.color = 'blue';
		}
	}
}
function codeCheck() {
	var id = document.getElementById("codeName").value;
	//var pattern = /[!@#$%^&*()+=/\?/'<">:,;.~`]/;
	
	if (id.value != '') {
		if (id.length < 6) {
			alert("패스워드는 6자이상 18자 이하만 가능합니다.");
			document.getElementById('rootCheck').innerHTML = '잘못된 유형입니다.';
			document.getElementById('rootCheck').style.color = 'red';
			document.getElementById('rootName').value = "";
			return false;
		} else {
			document.getElementById('rootCheck').innerHTML = '사용가능한 유형입니다.';
			document.getElementById('rootCheck').style.color = 'blue';
		}
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
<%-- 팀로그인 --%>
<section style="margin: 0px auto;" style="margin: 0px auto;">
<article id="login" style="display:''">
<fieldset style="width: 900px; align-content: center;" class="container-fluid">
<legend>[ TEAM FOLDER ]</legend>
<form id="webFoler_form" action="teamLogin.BT" method="post">
<input name="wrootName" type="text" placeholder="Team Folder" maxlength="12" required="required"><br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input name="wcodeName" type="password" placeholder="Team Code"  maxlength="18" required="required">
<input type="submit" class="btn btn-primary btn-xs" value="확인"><br>
<br>
<input type="button" class="btn btn-primary btn-xs" value="팀 폴더 생성" onclick="rootCreateShow()">
<input type="button" class="btn btn-primary btn-xs" value="CODE 찾기" onclick="findCodeShow()">
</form>
</fieldset>
</article>
<%-- 루트 폴더 생성 --%>
<article id="rootCreate" style="display:none">
<fieldset style="width: 900px; align-content: center;" class="container-fluid">
<legend>[ 팀 폴더 생성 ]</legend>
<form name="rootCreate" action="rootCreate.BT">
<input type="text" name="wrootName" id="rootName" maxlength="12" placeholder="Folder Name" onChange="rootCheck()">
<br><span id="rootCheck"></span><br>
<input type="password" name="wcodeName" id="codeName" maxlength="18" placeholder="Code" onChange="codeCheck()">
<br><span id="codeCheck"></span><br>
<input type="submit" class="btn btn-primary btn-xs" value="생성" onclick="loginShow()">
<input type="button" class="btn btn-primary btn-xs" value="취소" onclick="loginShow()">
</form>
</fieldset>
</article>
<%-- 코드 찾기 --%>
<article id="findCode" style="display:none">
<fieldset style="width: 900px; align-content: center;" class="container-fluid">
<legend>[ CODE 찾기 ]</legend>
<form name="findCode" action="findCode.BT">
<input type="text" name="rootName" placeholder="폴더 이름 입력"><br>
<input type="submit" class="btn btn-primary btn-xs" value="찾기">
<input type="button" class="btn btn-primary btn-xs" value="취소" onclick="loginShow()">
</form>
</fieldset>
</article>
</section>
<%@ include file="../footer.jsp"%>
</body>
</html>