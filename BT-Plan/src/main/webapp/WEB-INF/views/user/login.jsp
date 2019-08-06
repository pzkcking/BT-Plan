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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script>
$(document).ready(function() {
	$("#test_btn").click(function() {
		$.ajax({
			url : 'bt.user.model/VerifyRecaptcha',
			type : 'post',
			data : {recaptcha : $("#g-recaptcha-response").val()},
			success : function(data) {
				switch (data) {
					case 0:
						alert("자동 가입 방지 봇 통과");
						break;
					case 1:
						alert("자동 가입 방지 봇을 확인 한뒤 진행 해 주세요.");
						break;
					default:
						alert("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다. [Error bot Code : " + Number(data) + "]");
						break;
				}
			}
		});
	});
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
<script>
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
<script type="text/javascript">
	/* reCAPTCHA 체크 확인 */
	function FormSubmit() {
		if (grecaptcha.getResponse() == "") {
			alert("자동 로그인 방지를 확인해주세요.");
			return false;
		} else {
			return true;
		}
	}
</script>
<body>
<div class='zeta-menu-bar'>
<ul class="zeta-menu" style="position: relative; z-index: 2;">
	<li><a href="#">BT-Plan</a></li>
	<li><a href="#">ID / PW 찾기 </a>
		<ul>
			<li><a href="findId.BT">ID 찾기</a></li>
			<li><a href="findPassword.BT">PW 찾기</a></li>
			<li><a href="emailReSend.BT">인증 메일 재발송</a></li>
		</ul>
	</li>
</ul>
</div>
<%-- login form --%>
<div class="container" style="position: relative; z-index: 1;">
<div class="col-lg-4"></div>
<div class="col-lg-4">
<%-- 로그인 정보 보안을 위해 post방식으로 전송 --%>
<form action="login_ok.BT" method="post" onSubmit="return FormSubmit();">
	<h3 style="text-align: center;">Sign in</h3>
	<div class="form-group">
		<input type="text" class="form-control" placeholder="아이디"
			name="Uuserid" value="${ saveid }" maxlength="20" required="required">
	</div>
	<div class="form-group">
		<input type="password" class="form-control" placeholder="비밀번호"
			name="Upassword" maxlength="20" required="required">
	</div>
	<input type=checkbox name="save" value="save" ${ checked }>
	아이디 저장 <input type="submit" class="btn btn-primary form-control" value="로그인">&nbsp;<br>
	<input type="submit" class="btn btn-primary form-control" value="취소" onclick="window.self.close()"><br>
</form>
</div>
<br>
<div class="g-recaptcha" data-sitekey="6Lfmm64UAAAAAMZPhmTDbPakyRJhZCizcH2rds82"></div>
</div>
</body>
</html>
