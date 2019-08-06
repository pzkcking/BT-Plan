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
</head>
<script type="text/javascript">
function inputPhoneNumber(obj) {
	var number = obj.value.replace(/[^0-9]/g, "");
	var phone = "";

	if (number.length < 4) {
		return number;
	} else if (number.length < 7) {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3);
	} else if (number.length < 11) {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3, 3);
		phone += "-";
		phone += number.substr(6);
	} else {
		phone += number.substr(0, 3);
		phone += "-";
		phone += number.substr(3, 4);
		phone += "-";
		phone += number.substr(7);
	}
	obj.value = phone;
}
$(function() {
	$(".zeta-menu li").hover(function() {
		$('ul:first', this).show();
	}, function() {
		$('ul:first', this).hide();
	});
	$(".zeta-menu>li:has(ul)>a").each(function() {
		$(this).html($(this).html() + ' &or;');
	});
	$(".zeta-menu ul li:has(ul)").find("a:first").append("<p style='float:right;margin:-3px'>&#9656;</p>");
});
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
	<li><a href="#">BT-Plan Password Find</a></li>
	<li><a href="#">ID / PW 찾기</a>
		<ul>
		<li><a href="findId.BT">ID 찾기</a></li>
		<li><a href="findPassword.BT">PW 찾기</a></li>
		</ul>
	</li>
</ul>
</div>
<%-- login form --%>
<div class="container" style="position: relative; z-index: 1;">
<div class="col-lg-4"></div>
<div class="col-lg-4">
<form name="findPassword" action="findPassword_ok.BT">
	<h3 style="text-align: center;">Password 찾기</h3>
	<div class="form-group">
		<input type="text" class="form-control" name="Uuserid" placeholder="아이디" required="required">
	</div>
	<div class="form-group">
		<select class="form-control">
			<option>질문 선택</option>
			<option>나의 보물 1호는?</option>
			<option>가장 기억에 남는 영화는?</option>
			<option>다녔던 초등학교의 이름은?</option>
			<option>지금 살고있는 곳은?</option>
		</select>
	</div>
	<div class="form-group">
		<input type="text" class="form-control" name="Usecondary" required="required">
	</div>
	<input type="submit" class="btn btn-primary form-control" value="찾기"><br>&nbsp;<br>
	<input type="submit" class="btn btn-primary form-control" value="취소" onclick="window.self.close()"><br>
</form>
</div>
</div>
</body>
</html>