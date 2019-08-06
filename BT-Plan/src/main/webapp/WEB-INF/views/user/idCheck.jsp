<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- Animation jQuery --%>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<%-- Bootstrap js --%>
<link rel='stylesheet' href="css/bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BT-Plan</title>
<script type="text/javascript">
var pw_passed = true;

function fn_pw_check() {
	var id = document.getElementById("Uuserid").value;	// 아이디

	pw_passed = true;

	var pattern1 = /[0-9]/;
	var pattern2 = /[a-z]/;
	var pattern3 = /[_]/;	// 원하는 특수문자 추가 제거
	var pw_msg = "";

	if (!pattern1.test(id) || !pattern2.test(id) || !pattern3.test(id)
			|| pw.length<5||pw.length>12) {
		alert("아이디는 5자이상 12자 이하만 가능합니다 특수문자는 _만 사용가능합니다.");
		return false;
	}
	return true;
}
function isSame() {
	var Uuserid = document.all.Uuserid.value;
	var id = document.getElementById("Uuserid").value;
	var pattern3 = /[!@#$%^&*()+=/\?/'<">:,;.~`]/;
	var pw_msg = "";

	if (document.all.Uuserid.value.indexOf(" ") >= 0) {
		alert("아이디에 공백을 사용할 수 없습니다.");
		document.getElementById('Uuserid').value = "";
		return false;
	}
	if (document.getElementById('Uuserid').value != '') {
		if (pattern3.test(id) || id.length < 5) {
			alert("아이디는 5자이상 12자 이하만 가능합니다 특수문자는 _만 사용가능합니다.");
			document.getElementById('same').innerHTML = '잘못된 아이디 유형입니다';
			document.getElementById('same').style.color = 'red';
			document.getElementById('Uuserid').value = "";
			return false;
		} else {
			document.getElementById('same').innerHTML = '사용가능합니다.';
			document.getElementById('same').style.color = 'blue';
		}
	}
}
</script>
<style>
body {
	margin: 0;
}
.zeta-menu-bar {
	background: grey;
	display: inline-block;
	width: 100%;
}
.zeta-menu {
	margin: 0;
	padding: 0;
}
.zeta-menu li {
	float: left;
	list-style: none;
	position: relative;
}
.zeta-menu li:hover {
	background: white;
}
.zeta-menu li:hover>a {
	color: lightgrey;
}
.zeta-menu a {
	color: white;
	display: block;
	padding: 10px 20px;
	text-decoration: none;
}
.zeta-menu ul {
	background: #eee;
	border: 1px solid lightgrey;
	display: none;
	padding: 0;
	position: absolute;
	left: 0;
	top: 100%;
	width: 180px;
}
.zeta-menu ul li {
	float: none;
}
.zeta-menu ul li:hover {
	background: #ddd;
}
.zeta-menu ul li:hover a {
	color: grey;
}
.zeta-menu ul a {
	color: black;
}
.zeta-menu ul ul {
	left: 100%;
	top: 0;
}
.zeta-menu ul ul li {
	float: left;
	margin-right: 10px;
}
</style>
</head>
<body>
<div class='zeta-menu-bar'>
	<ul class="zeta-menu" style="position: relative; z-index: 2;">
		<li><a href="#">BT-Plan</a></li>
	</ul>
</div>
<%-- login form --%>
<div class="container" style="position: relative; z-index: 1;">
<div class="col-lg-4"></div>
<div class="col-lg-4">
<form name="userIdCheck" action="idCheck_ok.BT" method="post">
<div style="border: 2px solid white; width: 205px; height: 230px; text-align: center;">
	<h3 style="text-align: center;">ID 중복검사</h3>
	<div class="form-group">
		<input type="text" name="Uuserid" class="form-control" size="8"
			required="required" maxlength="12" id="Uuserid" onchange="isSame()">
		<span id="same"></span>
	</div>
	<input type="submit" value="찾기" class="btn btn-primary form-control">&nbsp;<br>
	<input type="submit" class="btn btn-primary form-control" value="취소" onclick="window.self.close()"><br>
</div>
</form>
</div>
</div>
</body>